/**
 * Created by wscown on 1/20/16.
 * Bug causes a null pointer exception when trying to prove function runs
 */

enum Colors{
    RED, GREEN, BLUE, YELLOW, PURPLE
}

class ColorfulThing{
    Colors color;

    ColorfulThing(Colors color){
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }
}

class ThingContainer{
    private ColorfulThing[] colorfulThings;
    private int arrayCounter = 0;

    ThingContainer(int arraySize){
        colorfulThings = new ColorfulThing[arraySize];
    }

    ThingContainer(ColorfulThing[] colorfulThings){
        this(colorfulThings.length);

        for(ColorfulThing c: colorfulThings){
            if(c != null) {
                this.add(c);
            }
        }
    }

    public String add(ColorfulThing c){

        String fullMessage = "ThingContainer is full";

        if(arrayCounter == colorfulThings.length){
            System.out.println(fullMessage);
            return fullMessage;
        }else
        {
            colorfulThings[arrayCounter] = c;
            if(arrayCounter - 1 != colorfulThings.length) {
                arrayCounter++;
            }
            return null;
        }
    }

    public void printThings(){

        for(int i = 0; i < arrayCounter; i++){
            System.out.println(colorfulThings[i].getColor());
        }
    }

    public ColorfulThing pop(){
        if(arrayCounter > 0) {
            ColorfulThing c = colorfulThings[arrayCounter - 1];
            deleteEntry(arrayCounter - 1);
            return c;
        }
        return null;
    }

    public ColorfulThing remove(Colors c){
        ColorfulThing result;

        for(int i = 0; i < arrayCounter; i++) {

            if(c == colorfulThings[i].getColor()){
                result = colorfulThings[i];
                deleteEntry(i);
                return result;
            }
        }
        return null;
    }

    public ColorfulThing remove(ColorfulThing ct){
        return remove(ct.getColor());
    }

    //Deletes object[i] from the array
    public void deleteEntry(int i){

        for(int k = i+1; k < colorfulThings.length; k++) {
            colorfulThings[k-1] = colorfulThings[k];
        }

        colorfulThings[colorfulThings.length - 1] = null;

        arrayCounter--;

    }

    public int getArrayCounter(){
        return arrayCounter;
    }

    public ColorfulThing getColorfulThingEntry(int i){
        return colorfulThings[i];
    }

    public ColorfulThing[] getColorfulThings(){
        return colorfulThings;
    }
}

public class InitializationLab2 {

    public static void main(String [] args){
        ThingContainer thingContainer1 = new ThingContainer(2);
        ThingContainer thingContainer2 = new ThingContainer(3);
        ThingContainer thingContainer3 = new ThingContainer(4);

        for(Colors c : Colors.values()){
                thingContainer1.add(new ColorfulThing(c));
                thingContainer2.add(new ColorfulThing(c));
                thingContainer3.add(new ColorfulThing(c));
        }

        System.out.println("Used pop on thingContainer1 and removed a ColorfulThing of enum type " + thingContainer1.pop().getColor());
        System.out.println("Used pop on thingContainer2 and removed a ColorfulThing of enum type " + thingContainer2.pop().getColor());
        System.out.println("Used pop on thingContainer3 and removed a ColorfulThing of enum type " + thingContainer3.pop().getColor());

        System.out.println("Used remove on thingContainer1 with an enum as a parameter to remove the first entry of type " + thingContainer1.remove(Colors.RED).getColor());
        System.out.println("Used remove on thingContainer2 with an enum as a parameter to remove the first entry of type " + thingContainer2.remove(Colors.RED).getColor());
        System.out.println("Used remove on thingContainer3 with an enum as a parameter to remove the first entry of type " + thingContainer3.remove(Colors.RED).getColor());

        thingContainer3.add(new ColorfulThing(Colors.PURPLE));
        System.out.println("Used remove on thingContainer1 with a ColorfulThing object as its parameter to remove the first entry of type " + thingContainer3.remove(new ColorfulThing(Colors.PURPLE)).getColor());


        ThingContainer thingContainer4 = new ThingContainer(thingContainer3.getColorfulThings());

        System.out.println("thingCoun3 " + thingContainer3.getArrayCounter());
        System.out.println("thingCoun4 " + thingContainer4.getArrayCounter());

        System.out.println("A test of the constructor that accepts an array of ColorfulThings. The following two ThingContainers should be equal:");
        System.out.println("ThingContainer1");
        thingContainer3.printThings();
        System.out.println("ThingContainer2");
        thingContainer4.printThings();

    }
}




