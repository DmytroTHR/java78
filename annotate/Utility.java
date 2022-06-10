package annotate;

@MostUsed
public class Utility {
    void doStuff(){
        System.out.println("Utility doStuff");
    }

    @MostUsed("AnnotationValueHere")
    void doStuff(String arg){
        System.out.println("Utility doStuff(String arg) " + arg);
    }

    void doStuff(int arg){
        System.out.println("Utility doStuff(int arg) " + arg);
    }
}

//has @MostUsed annotation
//because it is @Inherited
class SubUtility extends Utility{

} 
