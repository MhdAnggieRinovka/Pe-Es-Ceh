import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @authorDenise ,Mhd Anggie,  Rajasa Cikal
 */

public class Puzzle {
	
	int gridSize; //membuat atribut int untuk ukuran puzzle
	LinkedList<Car> cars; //menyatakan object object car yang ada
	Car crashedCar; //membuat object baru yang berupa mobil penghalang

	public Puzzle(int gridSize, LinkedList<Car> cars) {
		super();//memanggil kelas atas
		this.gridSize = gridSize; //menginisialisasi ukuran puzzle dengan gridSize yang dikimkan dari kelas RushHour 
		this.cars = cars; //menginisialisasi car dengan car yang dikirimkan dari kelas RushHour
	}
	
	public Car getRedCar(){ //fungsi ini mendapatkan object redCar
		return cars.get(0); //mengembalikan car pada posisi ke 0 karena merupakan PriorityQueue
	}
	
	public boolean canMoveDown(Car car) {// menentukan apakah bisa bergerak kebawah
		if(car.x + car.size < gridSize && !crashCars(car.x+car.size, car.y)) //mementukan apakah posisi x+ukuran car kecil dari ukuran puzzle,
                                                                                     //serta nilai crashCars(posisi car berdasarkan x + ukuran car dengan posisi car berdasarkan y) 
                                                                                     //bernilai false
                    
			return true;//jika memenuhi syarat maka mengembalikan nilai true
		return false;// jika tidak mengembalikan nilai false
	}
	
	public boolean canMoveUp(Car car) { // menentukan apakah bisa bergerak keatas
		if(car.x > 0 && !crashCars(car.x-1, car.y)) //menentukan apakah posisi car.x lebih besar dari 0 serta crashCars
                    //(posisi car berdasarkan x-1,posisi car berdasarkan y) bernilai false
			return true;// jika memenuhi syarat maka mengmbalikan nilai true
		return false; // jika tidak maka mengembalikan nilai false
	}
	
	public boolean canMoveRight(Car car) {// menentukan apakah bisa bergerak kekanan
		if(car.y + car.size < gridSize && !crashCars(car.x, car.y+car.size))//menentukan apakah posisi car berdasarkan y ditambah ukuran car  
                    //lebih kecil dari ukuran puzzle dan(posisi car berdasarkan x,posisi car berdasarkan y + ukuran car) bernilai false             
			return true;// jika memenuhi syarat maka mengmbalikan nilai true
		return false; // jika tidak memenuhi syarat maka mengmbalikan nilai false
	}
	
	public boolean canMoveLeft(Car car) { // menentukan apakah bisa bergerak kekiri
		if(car.y > 0 && !crashCars(car.x, car.y-1))//menentukan apakah posisi car berdasarkan y  
                    //lebih besar dari 0 dan(posisi car berdasarkan x,posisi car berdasarkan y - 1) bernilai false      
			return true;// jika memenuhi syarat maka mengmbalikan nilai true
		return false; // jika tidak memenuhi syarat maka mengmbalikan nilai false
	}
	
	public boolean crashCars(int x, int y) {// menentukan apakah ada tabrakan mobil
		for(Car car : cars){ //lopping untuk object car yang bernama cars
			if(car.isHorizontal()){// cek apakah car tersebut horizontal 
				if(x == car.x && y >= car.y && y < car.y + car.size){// jiks horizontal,lalu menentukan apakah posisi x = posisi car berdasarkan x
                                    //serta posisi y >= posisi car berdasarkan y dan posisi y < posisi car berdasarkan y + ukuran car
					crashedCar = car; //nilai crashedCar akan bernilai sama dengan car
					return true; //jika memenuhi syarat maka mengmbalikan true
				}
			}
			else if(car.isVertical()){// menentukan car tersebut vertical 
				if(y == car.y && x >= car.x && x < car.x + car.size){// jika vertical,lalu menentukan apakah posisi y = posisi car berdasarkan y
                                    //serta posisi x >= posisi car berdasarkan x dan posisi x < posisi car berdasarkan x + ukuran car
					crashedCar = car; //nilai crashedCar akan bernilai sama dengan car
					return true; //jika memenuhi syarat maka mengmbalikan true
				}
			}
		}
		return false; // jika tidak memenuhi syarat maka mengembalikan false
	}
	
}
