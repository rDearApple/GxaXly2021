package com.gxa.xly2021;

import com.gxa.xly2021.dao.DeptDao;
import com.gxa.xly2021.dao.EmplDao;
import com.gxa.xly2021.dao.PermissionDao;
import com.gxa.xly2021.entity.*;
import com.gxa.xly2021.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@SuppressWarnings("all")
class Xly2021ApplicationTests {

    /**
     * 注入dao层对象
     */
    @Autowired
    private DeptDao deptDao;

    @Test
    public void testFindAll(){
        // 调用查询方法
        List<Dept> depts = deptDao.findAll();
        // 遍历
        for (Dept dept : depts) {
            System.out.println(dept);
        }
    }

    @Autowired
    private EmplDao emplDao;

    @Test
    public void testLogin(){
        Empl admin = emplDao.findByAccount("admin");
        for (Role role : admin.getRoles()) {
            List<Permission> permissions = role.getPermissions();
            for (Permission permission : permissions) {
                System.out.println(permission);
            }
        }
    }


    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void testPermission(){
        // 权限数据
        List<Permission> permissions = permissionDao.findAll();
        // 装菜单的容器
        List<Menu> menus = new ArrayList<>();

        // 先处理一级菜单
        for (Permission permission : permissions) {
            // 只处理一级菜单
            if (permission.getParentId().equals(0) && permission.getPermissionLevel().equals(1)) {
                menus.add(new Menu(
                        permission.getPermissionId(),
                        permission.getPermissionName(),
                        permission.getPermissionUrl(),
                        permission.getPermissionLevel(),
                        permission.getPermissionOrder()));
            }
        }

        // 处理二级菜单
        for (Menu menu : menus) {
            // 子菜单的集合
            List<Menu> subMenus = new ArrayList<>();
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
            menu.setSubMenus(subMenus);
        }


        for (Menu menu : menus) {
            System.out.println(menu);
        }


    }

    @Test
    public void TEST(){
        System.out.println(MD5Util.MD55("1999119"));
    }
}
