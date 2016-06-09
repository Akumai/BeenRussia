package drago.beenrussia;

/**
 * Класс описывающий регионы с кодом и состоянием выбранности
 */
public class Region {
    private String name = null;
    private String code = null;
    private boolean selected = false;

    public Region(String name, String code, boolean selected) {
        super();
        this.name = name;
        this.code = code;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
