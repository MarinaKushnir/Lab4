package com.company;

/* Создать производный класс
– правильная пирамида с апофемой h (апофема – высота какой-либо боковой стороны), добавить в
класс метод определения объема фигуры, перегрузить методы расчета площади и вывода сведений о
фигуре.

*/
public class Pyramid extends Square{
    final double NumberForVolume=0.33;
    final double NumberOfSides=4;
    final double apothem;
    public Pyramid (){
        apothem= NumberOfSides*side+side;
    }

    public double getVolume(){
        return this.apothem*NumberForVolume*this.getAreaSq();
    }
    public double getArea(){
        return this.getAreaSq()+this.apothem*side*NumberOfSides/2;
    }
    @Override
    public String toString(){
        if ( apothem!=0){
            return"Pyramid:"+
                    "\n\tbase side = "+String.format("%6.2f",side)+
                    "\n\tapothem = "+String.format("%6.2f",apothem)+
                    "\n\tvolume = "+String.format("%6.2f",getVolume())+
                    "\n\tarea = "+String.format("%6.2f",getArea());
        }
        else
            return"Pyramid:"+
                    "\n\tside = 0";
    }


}
