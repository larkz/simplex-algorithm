package main

import simplex.SimplexRun

object SimplexMain {

  def main(args: Array[String]): Unit = {
    val tab = Array(
      Array[Double](1,2,1,0,0,0,16),
      Array[Double](1,1,0,1,0,0,9),
      Array[Double](3,2,0,0,1,0,24),
      Array[Double](-40,-30,0,0,1,0)
    )
    val red = SimplexRun.solveSimplex(tab)
    // println(red(0)(0))
    SimplexRun.printTableau(red)
  }
}
