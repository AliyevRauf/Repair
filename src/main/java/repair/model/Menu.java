package repair.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 6/13/2018.
 */
public class Menu {

    private List<String> menus;

    public Menu(){
        menus=new ArrayList<>();
        String menu1=" <li><a class=\"app-menu__item active\" href=\"index.html\"><i class=\"app-menu__icon fa fa-building\"></i><span class=\"app-menu__label\">Branches</span></a></li>";
        String menu2="";
        String menu3="";
        String menu4="";
        String menu5="";

        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
    }

    public List<String> getMenus(){
        return menus;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
