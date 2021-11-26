package constanse;

public enum ButtonAction {

    CREATE("Create"), VIEW("View"), EDIT("Edit"),SAVE("Save"), DELETE("Delete");
    private String action = null;

    ButtonAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
