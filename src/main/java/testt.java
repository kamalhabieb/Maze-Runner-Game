import models.facade.Facade;

public class testt {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.initializeGame(Facade.EASY);
//        String testt =
//                "r,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w\n" +
//                "r,b,b,b,b,b,b,w,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,w,b,b,b,b,b,w,b,b,b,w,b,b,b,b,b,b,b,w\n" +
//                "r,w,b,w,w,w,b,w,w,w,w,w,w,w,w,w,b,w,b,w,w,w,b,w,w,w,w,w,b,w,b,w,w,w,b,w,w,w,w,w,b,w\n" +
//                "r,w,b,b,b,w,b,b,b,b,b,w,b,b,b,b,b,w,b,w,b,w,b,b,b,w,b,b,b,w,b,w,b,b,b,b,b,w,b,w,b,w\n" +
//                "r,w,w,w,b,w,w,w,w,w,b,w,b,w,w,w,w,w,w,w,b,w,w,w,w,w,b,w,b,w,b,w,b,w,w,w,b,w,b,w,b,w\n" +
//                "r,w,b,b,b,b,b,b,b,w,b,b,b,w,b,b,b,b,b,b,b,w,b,w,b,w,b,w,b,w,b,w,b,w,b,w,b,b,b,w,b,w\n" +
//                "r,w,w,w,w,w,b,w,b,w,w,w,b,w,w,w,w,w,w,w,b,w,b,w,b,w,w,w,b,w,b,w,b,w,b,w,w,w,w,w,b,w\n" +
//                "r,w,b,w,b,b,b,w,b,b,b,w,b,b,b,b,b,w,b,b,b,w,b,b,b,b,b,b,b,w,b,b,b,b,b,w,b,b,b,w,b,w\n" +
//                "r,w,b,w,b,w,w,w,w,w,w,w,w,w,b,w,w,w,w,w,b,w,w,w,w,w,b,w,b,w,b,w,w,w,w,w,b,w,w,w,b,w\n" +
//                "r,w,b,w,b,w,b,b,b,b,b,b,b,w,b,w,b,w,b,b,b,b,b,w,b,b,b,w,b,w,b,b,b,w,b,b,b,w,b,b,b,w\n" +
//                "r,w,b,w,w,w,b,w,w,w,w,w,w,w,b,w,b,w,b,w,w,w,w,w,b,w,w,w,w,w,b,w,w,w,w,w,b,w,b,w,w,w\n" +
//                "r,w,b,b,b,b,b,w,b,b,b,b,b,b,b,b,b,w,b,b,b,b,b,b,b,w,b,b,b,w,b,b,b,b,b,w,b,b,b,w,b,w\n" +
//                "r,w,b,w,w,w,b,w,w,w,w,w,b,w,b,w,w,w,w,w,b,w,b,w,b,w,w,w,b,w,w,w,b,w,w,w,w,w,w,w,b,w\n" +
//                "r,w,b,w,b,b,b,w,b,b,b,w,b,w,b,w,b,w,b,w,b,w,b,w,b,w,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,w\n" +
//                "r,w,b,w,b,w,w,w,b,w,b,w,b,w,w,w,b,w,b,w,w,w,b,w,w,w,w,w,w,w,w,w,b,w,w,w,w,w,w,w,b,w\n" +
//                "r,w,b,w,b,b,b,b,b,w,b,w,b,b,b,w,b,b,b,w,b,w,b,w,b,b,b,b,b,b,b,w,b,b,b,b,b,b,b,w,b,w\n" +
//                "r,w,w,w,w,w,w,w,b,w,w,w,w,w,b,w,w,w,b,w,b,w,b,w,w,w,b,w,w,w,w,w,w,w,w,w,b,w,w,w,b,w\n" +
//                "r,w,b,b,b,b,b,b,b,b,b,b,b,w,b,b,b,b,b,b,b,w,b,b,b,w,b,w,b,b,b,w,b,b,b,b,b,b,b,w,b,w\n" +
//                "r,w,b,w,w,w,w,w,w,w,b,w,w,w,w,w,b,w,b,w,w,w,b,w,b,w,b,w,b,w,w,w,b,w,b,w,w,w,w,w,w,w\n" +
//                "r,w,b,w,b,w,b,b,b,b,b,b,b,b,b,b,b,w,b,b,b,b,b,w,b,b,b,w,b,w,b,w,b,w,b,w,b,w,b,b,b,w\n" +
//                "r,w,b,w,b,w,w,w,b,w,b,w,b,w,w,w,b,w,b,w,w,w,w,w,w,w,b,w,b,w,b,w,b,w,b,w,b,w,b,w,b,w\n" +
//                "r,w,b,b,b,w,b,w,b,w,b,w,b,w,b,b,b,w,b,w,b,b,b,b,b,w,b,b,b,b,b,w,b,w,b,b,b,b,b,w,b,w\n" +
//                "r,w,w,w,b,w,b,w,w,w,w,w,w,w,w,w,w,w,b,w,w,w,b,w,b,w,w,w,b,w,b,w,w,w,b,w,w,w,w,w,w,w\n" +
//                "r,w,b,b,b,w,b,b,b,b,b,b,b,b,b,b,b,w,b,w,b,w,b,w,b,w,b,b,b,w,b,b,b,b,b,b,b,w,b,w,b,w\n" +
//                "r,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,b,w,w,w,b,w,w,w,b,w,b,w,w,w,b,w,w,w,b,w,b,w,b,w,b,w\n" +
//                "r,w,b,b,b,b,b,b,b,b,b,b,b,w,b,b,b,b,b,b,b,w,b,b,b,b,b,w,b,b,b,b,b,w,b,w,b,b,b,b,b,w\n" +
//                "r,w,b,w,w,w,b,w,b,w,w,w,b,w,w,w,w,w,w,w,b,w,b,w,w,w,w,w,w,w,w,w,w,w,b,w,w,w,w,w,w,w\n" +
//                "r,w,b,b,b,w,b,w,b,b,b,w,b,b,b,b,b,w,b,b,b,w,b,w,b,b,b,b,b,b,b,b,b,b,b,b,b,b,b,w,b,w\n" +
//                "r,w,b,w,b,w,w,w,w,w,b,w,b,w,b,w,b,w,b,w,w,w,w,w,w,w,b,w,w,w,w,w,b,w,w,w,b,w,w,w,b,w\n" +
//                "r,w,b,w,b,b,b,b,b,w,b,w,b,w,b,w,b,b,b,w,b,b,b,w,b,b,b,w,b,b,b,b,b,b,b,w,b,w,b,b,b,w\n" +
//                "r,w,b,w,w,w,w,w,w,w,b,w,w,w,w,w,w,w,b,w,w,w,b,w,w,w,w,w,b,w,b,w,b,w,w,w,b,w,b,w,w,w\n" +
//                "r,w,b,b,b,b,b,w,b,w,b,b,b,b,b,b,b,w,b,b,b,b,b,b,b,w,b,w,b,w,b,w,b,b,b,w,b,b,b,b,b,w\n" +
//                "r,w,w,w,b,w,b,w,b,w,w,w,w,w,w,w,w,w,b,w,w,w,b,w,b,w,b,w,b,w,w,w,b,w,w,w,b,w,w,w,w,w\n" +
//                "r,w,b,w,b,w,b,b,b,w,b,b,b,w,b,b,b,b,b,w,b,b,b,w,b,b,b,w,b,w,b,w,b,w,b,b,b,b,b,b,b,w\n" +
//                "r,w,b,w,b,w,b,w,w,w,w,w,b,w,w,w,b,w,w,w,w,w,b,w,b,w,w,w,w,w,b,w,b,w,w,w,b,w,w,w,b,w\n" +
//                "r,w,b,w,b,w,b,w,b,b,b,w,b,w,b,b,b,b,b,w,b,b,b,w,b,b,b,b,b,b,b,b,b,w,b,w,b,w,b,w,b,w\n" +
//                "r,w,b,w,w,w,w,w,b,w,b,w,b,w,b,w,w,w,w,w,b,w,w,w,b,w,w,w,w,w,b,w,w,w,b,w,b,w,b,w,b,w\n" +
//                "r,w,b,b,b,b,b,b,b,w,b,b,b,b,b,b,b,w,b,w,b,w,b,b,b,w,b,w,b,w,b,w,b,w,b,b,b,b,b,w,b,w\n" +
//                "r,w,w,w,b,w,w,w,w,w,w,w,w,w,w,w,w,w,b,w,b,w,b,w,w,w,b,w,b,w,b,w,b,w,w,w,w,w,b,w,b,w\n" +
//                "r,w,b,b,b,b,b,b,b,w,b,b,b,b,b,b,b,b,b,b,b,w,b,w,b,b,b,b,b,b,b,b,b,b,b,w,b,b,b,w,b,b\n" +
//                "r,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w,w\n" +
    }
}
