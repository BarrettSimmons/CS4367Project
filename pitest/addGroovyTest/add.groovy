//we dont need to compile the .groovy file then run it
//ex javac addJava.java 
//java addJava 

//no import needed 


 
class add {
//the main method doesn't need to be public 
    static void main(String[] args) {
    	x = 1;
    	y = 2;
    	z = 3; 

    	z = x + y;

    	if(z == 3)
    		print(z);
   }
}

public class addClass {
	int x;
	int y;
	int z = 0;

	public void setZ(){
		this.z = this.x + this.y;
	}

	public int DisplayZ() {
		return z;
		}
	}

}
