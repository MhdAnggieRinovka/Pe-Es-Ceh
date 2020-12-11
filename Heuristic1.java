/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Denise ,Mhd Anggie,  Rajasa Cika
 */

public class Heuristic1 extends Heuristic{
	
	@Override
	public int getValue(State state) {
		Car redcar = state.puzzle.getRedCar(); //Membuat object baru bertipe Car yang bernama redCar
		int gridSize = state.puzzle.gridSize; // mengambil ukuran puzzle
		int numberOfMovements = 0; //menghitung berapa banyak object redCar melangkah
		for (int i = redcar.y+2; i < gridSize; i++) { //selama titik perpindahannya tidak melebihi kotaknya maka masuk looping
			int x = redcar.x; //memanggil posisi awal dari redCar berdasarkan titik x
			int y = i; //memanggil posisi awal y dari nilai i
			if(state.puzzle.crashCars(x, y)){
				Car car = state.puzzle.crashedCar;  //membuat object car yang menandakan penghalan si mobilnya
				if(!state.puzzle.canMoveDown(car) && !state.puzzle.canMoveUp(car)) //menentukan apakah car tidak bisa kebawah dan ke atas
					numberOfMovements += 2; // jika tidak bisa maka numberOfMovementsnya bertambah 2
				else
					numberOfMovements ++; // jika bisa maka numberOfMovementsnya bertambah 1
			}
		}
		return numberOfMovements; // mengembalikan nilai numberOfMovements
	}

}
