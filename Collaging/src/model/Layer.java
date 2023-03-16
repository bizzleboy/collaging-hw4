package model;

import java.util.List;

import model.Image;

public class Layer {
  List<Image> imagesOnLayer;
  private String filter;
  String name;

  public Layer(String name){
    this.name = name;
  }


  public void applyFilter(String filter){
   switch(filter){
     case "normal":
       //pass
       break;
     case"red-component":
       for(Image image:imagesOnLayer){
         image.filterImageRed();
         break;
         }
     case"blue-component":
       for(Image image:imagesOnLayer){
         image.filterImageBlue();
         break;
       }
     case"green-component":
       for(Image image:imagesOnLayer){
         image.filterImageGreen();
         break;
       }
     case"brighten-value":
       for(Image image:imagesOnLayer){
         image.brightenImage("brighten-value");
         break;
       }
     case"darken-value":
       for(Image image:imagesOnLayer){
         image.darkenImage("darken-value");
         break;
       }
     case"brighten-luma":
       for(Image image:imagesOnLayer){
         image.brightenImage("brighten-luma");
         break;
       }
     case"darken-luma":
       for(Image image:imagesOnLayer){
         image.darkenImage("darken-luma");
         break;
       }
     case"brighten-intensity":
       for(Image image:imagesOnLayer){
         image.brightenImage("brighten-intensity");
         break;
       }
     case"darken-intensity":
       for(Image image:imagesOnLayer){
         image.darkenImage("darken-intensity");
         break;
       }
     default: System.out.println("Invalid Input!");
       }

   }

  }



