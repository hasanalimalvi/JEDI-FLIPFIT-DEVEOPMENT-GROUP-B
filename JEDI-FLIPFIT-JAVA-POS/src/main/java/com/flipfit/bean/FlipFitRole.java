package com.flipfit.bean;

import com.flipfit.constant.ColorConstants;

public class FlipFitRole {
    private int roleId;
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "\n" + ColorConstants.BLUE +
                "╔════════════════════════════════════════════════╗\n" +
                "║              🧬 FLIPFIT ROLE DETAILS           ║\n" +
                "╠════════════════════════════════════════════════╣\n" +
                String.format("║ 🆔 Role ID       : %-27s ║\n", roleId) +
                String.format("║ 🏷️ Role Name     : %-27s ║\n", roleName) +
                "╚════════════════════════════════════════════════╝" +
                ColorConstants.RESET;
    }

}
