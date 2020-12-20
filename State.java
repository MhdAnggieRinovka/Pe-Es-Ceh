import java.util.ArrayList;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Denise ,Mhd Anggie,  Rajasa Cikal
 */

public class State {
	
	Puzzle puzzle;//membuat object puzzle yang bertipekan puzzle
	int cost;//membuat ukuran berapa banyak langkah yang akan dihabiskan untuk sampai tujuan dari titik awal
	
	
	public State(Puzzle puzzle) {
		super();//memanggil kelas atas
		this.puzzle = puzzle;// menginisialisasi object puzzle dengan puzzle
		this.cost = 10000000;// memasukkan nilai (nilai awal saja) ke variable cost
	}
	
	public ArrayList<State> getNeighbors(){ //method untuk mendapatkan tetangga dari sebuah car
		ArrayList<State> neighbors = new ArrayList<>();
		LinkedList<Car> cars = this.puzzle.cars; //membuat linkedList baru berobjekkan car yang berisikan cars dari kelas Puzzle
		for (int i = 0; i < cars.size(); i++) { //selama posisi titik awal lebih kecil dari mobil
			Car car = cars.get(i); //untuk mendapat posisi dari puzzle yang available
			if(car.isVertical()){ //jika posisi dari mobil adalah Vertikal
				LinkedList<Car> newcars = cloneCars(cars); //membuat linkedList berobjekan Car dengan memanggil cloneCars 
				Car newcar = newcars.get(i); //membuat variable baru untuk mendapat posisi yang available 
				while(puzzle.canMoveDown(newcar)){ //looping selama car bisa berpindah ke bawah, pengecekan dengan memanggil canMoveDown dari kelas puzzle
					newcar.moveDown(); //newcar akan berpindah ke bawah, dengan memanggil moveDown di dalam kelas car
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));// newcar akan ditambahkan menjadi neighboor baru dalam puzzle
					newcars = cloneCars(newcars);//memanggil variable newcars baru untuk dimasukkan kedalam newcars
					newcar = newcars.get(i); //untuk mendapat posisi yang available
				}
				newcars = cloneCars(cars);//memanggil variable newcars baru untuk dimasukkan kedalam newcars
				newcar = newcars.get(i); //untuk mendapat posisi yang available
				while(puzzle.canMoveUp(newcar)){ //looping selama car bisa berpindah ke atas, pengecekan dengan memanggil canMoveUp dari kelas puzzle
					newcar.moveUp(); //newcar akan berpindah ke atas, dengan memanggil moveUp di dalam kelas car
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));// newcar akan ditambahkan menjadi neighboor baru dalam puzzle
					newcars = cloneCars(newcars);//memanggil variable newcars baru untuk dimasukkan kedalam newcars
					newcar = newcars.get(i); //untuk mendapat posisi yang available
				}
			}
			else if(car.isHorizontal()){ //jika posisi dari mobil adalah Horizontal
				LinkedList<Car> newcars = cloneCars(cars); //membuat linkedList berobjekan Car dengan memanggil cloneCars 
				Car newcar = newcars.get(i); //membuat variable baru untuk mendapat posisi yang available 
				while(puzzle.canMoveRight(newcar)){ //looping selama newcar bisa berpindah ke kanan didalam puzzle, pengecekan dengan memanggil canMoveRight dari kelas puzzle
					newcar.moveRight(); //newcar akan berpindah ke sebelah kanan, dengan memanggil moveRight di dalam kelas car
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));// newcar akan ditambahkan menjadi neighboor baru dalam puzzle
					newcars = cloneCars(newcars);//memanggil variable newcars baru untuk dimasukkan kedalam newcars
					newcar = newcars.get(i); //untuk mendapat posisi yang available
				}
				newcars = cloneCars(cars); //membuat car baru
				newcar = newcars.get(i); //untuk mendapat posisi yang available 
				while(puzzle.canMoveLeft(newcar)){ //looping selama newcar bisa berpindah ke kiri didalam puzzle, pengecekan dengan memanggil canMoveLeft dari kelas puzzle
					newcar.moveLeft(); //newcar akan berpindah ke sebelah kiri , dengan memanggil moveLeft di dalam kelas car
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars))); // newcar akan ditambahkan menjadi neighboor baru dalam puzzle
					newcars = cloneCars(newcars); //memanggil variable newcars untuk dimasukkan kedalam newcars
					newcar = newcars.get(i); //untuk mendapat posisi yang available
				}
			}
		}
		
		return neighbors; //mengembalikan neighboor yang merupakan ArrayList dari neighbors yang telah ditambahkan sebelumnya.
	}
	
	private LinkedList<Car> cloneCars(LinkedList<Car> cars) {//membuat object-object cars
		LinkedList<Car> newcars = new LinkedList<>();// membuat linklist untuk car baru
		for(Car car : cars){// loop object car lalu masukkan ke variable yang bernama cars
			newcars.add(car.clone());// menambahkan object baru bertipe cars ke newCars
		}
		return newcars;// mengembalikan LinkedList yang berisi newCars yang berisi clone dari car
	}


	public boolean isGoal(){// cek apakah sudah sampai tujuan atau belum
		return puzzle.getRedCar().y == puzzle.gridSize - 2; // menentukan apakah nilai dari posisi redCar berdasarkan y = ukuran puzzle -2
                //jika memenuhi syarat mengembalikan true
                //jika tidak maka mengembalikan false
	}
	
	public void print(){// untuk print sebuah string
		System.out.println(this.toString());//print tiap baris dari hasi yang telah diterima dari method toString
	}
	
	public String toString(){ //Untuk mengeluarkan output dalam bentuk format String
		char[][] output = new char[puzzle.gridSize][puzzle.gridSize]; //variable ukuran grid puzzle untuk menyimpan ke array of char dari kelas Puzzle
		for (int i = 0; i < output.length; i++) { //selama i (baris) tidak melebihi panjang output maka masuk ke dalam loop
			for (int j = 0; j < output.length; j++) { //selama j (kolom) tidak melebihi panjang output maka masuk ke dalam loop
				output[i][j] = '.'; //variable char output i dan j akan mengeluarkan String .
			}
		}
		for(Car car : puzzle.cars){ //membuat object cars yang akan dimasukkan ke dalam linkedList<car>
			if(car.isHorizontal()){ //selama posisi car horizontal
				if(car.size == 2){ //selama ukuran dari car adalah 2 (berapa banyak kotak yang dipakai)
					if(car.equals(puzzle.getRedCar())){ //mengecak apakah objek car yang baru sama dengan objek car yang sudah ada
						output[car.x][car.y] = '='; //jika titik pertama untuk mobil adalah red car maka keluaranya adalah =
						output[car.x][car.y+1] = '='; //jika titik kedua untuk mobil adalah red car maka keluaranya adalah =
					}
					else{
						output[car.x][car.y] = '*'; //jika terjadi crash pada titik pertama maka keluarannya adalah *
						output[car.x][car.y+1] = '*'; //jika terjadi crash pada titik kedua maka keluarannya adalah *
					}
				}
				else if(car.size == 3){ //selama ukuran dari car adalah 3 (berapa banyak kotak yang dipakai) dan berorientasi Horizontal 
                                                        //dan ditandai oleh simbol pagar(#)
					output[car.x][car.y] = '#'; //untuk posisi pertama mobil yang berukuran 3 maka keluarannya adalah #
					output[car.x][car.y+1] = '#';//untuk posisi kedua mobil yang berukuran 3 maka keluarannya adalah #
					output[car.x][car.y+2] = '#';//untuk posisi ketiga mobil yang berukuran 3 maka keluarannya adalah #
				}
			}
			else if(car.isVertical()){ //selama posisi car adalah Vertical maka keluarannya akan ditandai oleh simbol plus(+)
                            
				if(car.size == 2){ //selama ukuran dari car adalah 2 (berapa banyak kotak yang dipakai) dan berorientasi Vertical 
                                                   //dan ditandai oleh simbol plus(+)
					output[car.x][car.y] = '+'; //untuk posisi pertama mobil yang berukuran 2 maka keluarannya adalah +
					output[car.x+1][car.y] = '+'; //untuk posisi kedua mobil yang berukuran 2 maka keluarannya adalah +
				}
				else if(car.size == 3){//selama ukuran dari car adalah 3 (berapa banyak kotak yang dipakai) dan berorientasi Vertical 
                                                   //dan ditandai oleh simbol add(@)
					output[car.x][car.y] = '@'; //untuk posisi pertama mobil yang berukuran 3 maka keluarannya adalah @
					output[car.x+1][car.y] = '@'; //untuk posisi kedua mobil yang berukuran 3 maka keluarannya adalah @
					output[car.x+2][car.y] = '@';//untuk posisi ketiga mobil yang berukuran 3 maka keluarannya adalah @
				}
			}
		}
		
		String result = ""; //atribut untuk menyimpan hasil
		for (int i = 0; i < output.length; i++) { //looping selama i tidak melebihi panjang output
			result += new String(output[i]) + "\n"; // akan mengeluarkan String dari output(yang berisi simbol-simbol) dan sebuah baris baru
		}
		
		return result; //mengembalikan hasil result
	}
	
	public Puzzle getPuzzle() {// mengambil object puzzle
		return puzzle; //mengembalikan object puzzle
	}
	
	public void setPuzzle(Puzzle puzzle) {// membuat object puzzle baru
		this.puzzle = puzzle;//menimpa object puzzle yang telah dibuat dengan object puzzle yang baru
	}
	
	public int getCost() {//mengambil nilai dari variable cost
		return cost;// mengembalikan nilai dari variable cost
	}
	
	public void setCost(int cost) {// membuat nilai cost baru
		this.cost = cost;// menimpa nilai dari cost yang lama dengan nilai yang baru
	}
	
	public State clone(){//Membuat keadaan baru dari puzzle yang telah dibuat 
		return new State(this.puzzle);// menginisialisasi kelas state dengan object puzzle
	}
	
}
