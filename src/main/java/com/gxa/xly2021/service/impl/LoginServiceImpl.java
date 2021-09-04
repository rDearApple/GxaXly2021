package com.gxa.xly2021.service.impl;

import com.gxa.xly2021.dao.EmplDao;
import com.gxa.xly2021.dao.PermissionDao;
import com.gxa.xly2021.dto.ResultDto;
import com.gxa.xly2021.entity.Empl;
import com.gxa.xly2021.entity.Menu;
import com.gxa.xly2021.entity.Permission;
import com.gxa.xly2021.entity.Role;
import com.gxa.xly2021.service.LoginService;
import com.gxa.xly2021.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;


/**
 * 登录的实现方法
 * @author 一路向北
 */
@Service
@SuppressWarnings("all")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private EmplDao emplDao;

    @Autowired
    private PermissionDao permissionDao;
    /**
     * 登录的抽象方法
     * @param empl
     * @return
     */
    @Override
    public ResultDto login(Empl empl, HttpSession session) {
        // 先进行数据校验

        // 进行数据库查询
        Empl dbEmpl = emplDao.findByAccount(empl.getEmplAccount());
        // 判断
        if (dbEmpl == null) {
            return new ResultDto(1001, "该用户不存在！");
        }
        // 判断密码
        if (!dbEmpl.getEmplPwd().equals(MD5Util.MD55(empl.getEmplPwd()))) {
            return new ResultDto(1002, "密码不正确！");
        }
        //判断这个用户是否为新用户
        if (dbEmpl.getLastLoginTime()==null||dbEmpl.getEmplPwd().equals(MD5Util.MD55("123123"))){
            return new ResultDto(1004,"你是新用户，请修改密码");
        }
        // 判断状态
        if (dbEmpl.getEmplStatus().equals(0)) {
            return new ResultDto(1003, "你已经被禁用了！请联系超级管理员");
        }

        // 修改登录时间
        // 构建一个新的员工对象， 里面只需要包含 最后登录时间和员工的ID
        // new Timestamp(System.currentTimeMillis()) 获取当前的时间
        emplDao.update(new Empl(dbEmpl.getEmplId(), new Timestamp(System.currentTimeMillis())));
        // 记录用户的登录状态
        session.setAttribute("emplName", dbEmpl.getEmplName());
        //保存用户id
        session.setAttribute("emplId",dbEmpl.getEmplId());
        // 保存用户所属的部门信息
        session.setAttribute("deptName", dbEmpl.getDept().getDeptFullName());
        // 保存用户的角色
        session.setAttribute("roleNames", getRolesStr(dbEmpl));
        // 保存是否是超级管理员的状态
        session.setAttribute("isSuper", isSuper(dbEmpl));
        // 将登录用户的菜单数据存放到session中
        session.setAttribute("menus", getMenus(dbEmpl));

        return new ResultDto(200, "登录成功了！");
    }


    /**
     * 获取菜单的方法
     * @param empl
     * @return
     */
    private List<Menu> getMenus(Empl empl){
        List<Permission> permissions = new ArrayList<>();
        List<Permission> util = new ArrayList<>();
        // 先判断是否是超级管理员
        if (isSuper(empl)) {
            // 权限数据从数据库中来
            permissions=permissionDao.findAll();

        }else{
            // 从员工对象中来
            for (Role role : empl.getRoles()) {
                if (role.getPermissions() == null) {
                    continue;
                }

                permissions.addAll(role.getPermissions());
            }
        }

        return  transMenu(permissions);

    }

    /**
     * 转换菜单的过程
     * @param permissions
     * @return
     */
    private List<Menu> transMenu(List<Permission> permissions){
        // 装菜单的容器
        List<Menu> menus = new ArrayList<>();
        // 去重的容器 => 存放无序唯一的数据
        // HashSet中如何判断是否包含某个元素？
        // hashCode+equals
        Set<Menu> menuSet = new HashSet<>();
        // 先处理一级菜单
        for (Permission permission : permissions) {
            // 只处理一级菜单
            if (permission.getParentId().equals(0) && permission.getPermissionLevel().equals(1)) {
                menuSet.add(new Menu(
                        permission.getPermissionId(),
                        permission.getPermissionName(),
                        permission.getPermissionUrl(),
                        permission.getPermissionLevel(),
                        permission.getPermissionOrder()
                    ));
            }
//            permissions.sort((o1, o2) -> o1.getPermissionOrder().compareTo(o2.getPermissionOrder()));
        }
        // 将set转换成List
        menus.addAll(menuSet);

        // 处理二级菜单
        for (Menu menu : menus) {
            // 子菜单的集合
            Set<Menu> subMenus = new HashSet<>();
            for (Permission permission : permissions) {
                // 判断权限是否是菜单子菜单
                if (permission.getPermissionLevel().equals(2) && permission.getParentId() != 0) {
                    // 是二级菜单
                    if (menu.getMenuId().equals(permission.getParentId())) {
                        // 说明当前的permission是menu的子菜单
                        subMenus.add(new Menu(
                                permission.getPermissionId(),
                                permission.getPermissionName(),
                                permission.getPermissionUrl(),
                                permission.getPermissionLevel(),
                                permission.getPermissionOrder()));
                    }
                }
            }

            // 将产生的子菜单添加到父级菜单中
            menu.getSubMenus().addAll(subMenus);
        }
        menus.sort((o1, o2) -> o2.getOrder().compareTo(o1.getOrder()));
        return menus;
    }



    /**
     * 判断是否是超级管理员
     * @param empl
     * @return
     */
    private Boolean isSuper(Empl empl){
        // 一个用户下面有多个角色
        List<Role> roles = empl.getRoles();
        for (Role role : roles) {
            // 判断是否是超级管理员
            if (role.getIsSuper().equals(1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从员工中获取角色字符串 =》 销售总监 , 销售经理
     * @param empl ： 员工对象
     * @return
     */
    private String getRolesStr(Empl empl){
        List<Role> roles = empl.getRoles();
        if (roles.size() == 0) {
            return "未分配角色";
        }
        // 多个角色之间给一个分隔符  ，
        String res = "";
        for (Role role : roles) {
            res += role.getRoleName()+" , ";
        }
        return res.substring(0, res.length()-3);
    }
    public List<Permission> search(List<Permission> logsList) {
        Collections.sort(logsList, new Comparator<Permission>() {
            @Override
            public int compare(Permission o1, Permission o2) {
                System.out.println(o1.getPermissionOrder());
                System.out.println(o2.getPermissionOrder());
                if ((o1.getPermissionOrder() > o2.getPermissionOrder())) {
                    return -1;
                }
                if (o1.getPermissionOrder() == o2.getPermissionOrder()) {
                    return 0;
                }
                return 1;
            }
        });
        return logsList;
    }
}