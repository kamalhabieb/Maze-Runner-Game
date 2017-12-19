package models.charcter.monsters;

import models.facade.ControlTower;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MonstersFactory {

    private static ArrayList<Class<? extends Monster>> MonstersType;
    public MonstersFactory() {
        MonstersType = new ArrayList<>();
        MonstersType.add(EasyMonster.class);
        MonstersType.add(MediumMonster.class);
        MonstersType.add(HardMonster.class);
    }

    public static Monster GetMonster(String monsterType, ControlTower controlTower) {
        Monster instance = null;
        for (int i = 0; i <MonstersType.size() ; i++) {
            if(monsterType.equalsIgnoreCase(MonstersType.get(i).getSimpleName())){
                try {
                    final Constructor<? extends Monster> constructor = MonstersType.get(i)
                            .getConstructor(ControlTower.class);
                        instance = constructor.newInstance(controlTower);
                        break;
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return instance;
    }
}
