package com.company;

import lombok.Getter;
import lombok.Setter;

/*Создать класс квадрат, члены класса – длина стороны. Предусмотреть в классе методы
вычисления и вывода сведений о фигуре – диагоналей, периметр, площадь.
*/

public class Square {
    final double NumberOfSides=4;
    @Getter @Setter double side;

    Square (){
        side= Math.random() * 11+1 ;
    }

    public double getAreaSq(){
        return this.side*side;
    }
    public double getPerimeter(){
        return this.side*NumberOfSides;
    }
    public double getDiagonal(){
        return this.side*Math.sqrt(2);
    }

    @Override
    public String toString(){
        if (side!=0){
            return"Square:"+
                    "\n\tside = "+String.format("%6.2f",side)+
                    "\n\tarea = "+String.format("%6.2f",getAreaSq())+
                    "\n\tperimeter = "+String.format("%6.2f",getPerimeter())+
                    "\n\tdiagonal = "+String.format("%6.2f",getDiagonal());
        }
        else
            return"Square:"+
                    "\n\tside = 0";
    }


}
