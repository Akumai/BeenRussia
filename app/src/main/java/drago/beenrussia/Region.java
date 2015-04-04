package drago.beenrussia;

/**
 * Класс описывающий регионы с кодом и состоянием выбранности
 */
public class Region {
    private String name = null;
    private boolean selected = false;

    public Region(String name, boolean selected) {
        super();
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
