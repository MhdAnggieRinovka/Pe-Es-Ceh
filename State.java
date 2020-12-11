import java.util.ArrayList;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS FX504
 */

public class State {
	
	Puzzle puzzle;//membuat object puzzle yang bertipekan puzzle
	int cost;//membuat ukuran berapa banyak langkah yang akan dihabiskan untuk sampai tujuan dari titik awal
	
	
	public State(Puzzle puzzle) {
		super();//memanggil kelas atas
		this.puzzle = puzzle;// menginisialisasi object puzzle
		this.cost = 10000000;// memasukkan nilai ke variable cost
	}
	
	public ArrayList<State> getNeighbors(){
		ArrayList<State> neighbors = new ArrayList<>();
		LinkedList<Car> cars = this.puzzle.cars; 
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			if(car.isVertical()){
				LinkedList<Car> newcars = cloneCars(cars);
				Car newcar = newcars.get(i);
				while(puzzle.canMoveDown(newcar)){
					newcar.moveDown();
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
				newcars = cloneCars(cars);
				newcar = newcars.get(i);
				while(puzzle.canMoveUp(newcar)){
					newcar.moveUp();
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
			}
			else if(car.isHorizontal()){
				LinkedList<Car> newcars = cloneCars(cars);
				Car newcar = newcars.get(i);
				while(puzzle.canMoveRight(newcar)){
					newcar.moveRight();
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
				newcars = cloneCars(cars);
				newcar = newcars.get(i);
				while(puzzle.canMoveLeft(newcar)){
					newcar.moveLeft();
					neighbors.add(new State(new Puzzle(this.puzzle.gridSize, newcars)));
					newcars = cloneCars(newcars);
					newcar = newcars.get(i);
				}
			}
		}
		
		return neighbors;
	}
	
	private LinkedList<Car> cloneCars(LinkedList<Car> cars) {//
		LinkedList<Car> newcars = new LinkedList<>();
		for(Car car : cars){
			newcars.add(car.clone());
		}
		return newcars;
	}


	public boolean isGoal(){// cek apakah sudah sampai tujuan atau belum
		return puzzle.getRedCar().y == puzzle.gridSize - 2; // menentukan apakah nilai dari posisi redCar berdasarkan y = ukuran puzzle -2
                //jika memenuhi syarat mengembalikan true
                //jika tidak maka mengembalikan false
	}
	
	public void print(){// untuk print sebuah string
		System.out.println(this.toString());//print tiap baris dari hasi yang telah diterima dari method toString
	}
	
	public String toString(){
		char[][] output = new char[puzzle.gridSize][puzzle.gridSize];
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < output.length; j++) {
				output[i][j] = '.';
			}
		}
		for(Car car : puzzle.cars){
			if(car.isHorizontal()){
				if(car.size == 2){
					if(car.equals(puzzle.getRedCar())){
						output[car.x][car.y] = '=';
						output[car.x][car.y+1] = '=';
					}
					else{
						output[car.x][car.y] = '*';
						output[car.x][car.y+1] = '*';
					}
				}
				else if(car.size == 3){
					output[car.x][car.y] = '#';
					output[car.x][car.y+1] = '#';
					output[car.x][car.y+2] = '#';
				}
			}
			else if(car.isVertical()){
				if(car.size == 2){
					output[car.x][car.y] = '+';
					output[car.x+1][car.y] = '+';
				}
				else if(car.size == 3){
					output[car.x][car.y] = '@';
					output[car.x+1][car.y] = '@';
					output[car.x+2][car.y] = '@';
				}
			}
		}
		
		String result = "";
		for (int i = 0; i < output.length; i++) {
			result += new String(output[i]) + "\n";
		}
		
		return result;
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
