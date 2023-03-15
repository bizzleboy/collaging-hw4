package model;

import java.util.List;

import model.Image;

public class Layer {
  List<Image> imagesOnLayer;
  private String filter;

  public Layer(){

  }

  public void applyFilter(String filter){
   switch(filter){
     case "normal":
       //pass
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
         image.brightenImage(1);
         break;
       }
     case"darken-value":
       for(Image image:imagesOnLayer){
         image.darkenImage(1);
         break;
       }
     default: System.out.println("Invalid Input!");
       }

   }

  }



