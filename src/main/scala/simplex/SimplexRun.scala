package simplex

object SimplexRun {
    
    def createTableau(nonBasicVariables: Array[Array[Double]]): Array[Array[Double]] = {
        // Enter in tableau directly, in the future create an input portal
        nonBasicVariables
    }
    
    def findPivotColumnIndex(tableau: Array[Array[Double]]): Int = {
        val lastRow = tableau.last
        lastRow.zipWithIndex.minBy(_._1)._2
    }

    def getMinZero(a: Array[Double]): Int = {
        var minInd = 0
        var minVal = a(0)
        for(i <- 1 to a.length - 1) {
            if(a(i) > 0 && a(i) < minVal){minVal = a(i); minInd = i}
        }
        minInd
    }

    // Smallest ratio
    def findPivotRowIndex(tableau: Array[Array[Double]], pivotColInd: Int ): Int = {
        val pivotCol = tableau.map(r => r(pivotColInd))
        val constraintCol = tableau.map(r => r.last)
        val ratios = constraintCol.zip(pivotCol).map { case (x, y) => x / y }
        getMinZero(ratios)
    }

    def pivot(tableau: Array[Array[Double]], pivotColInd: Int, pivotRowInd: Int): Array[Array[Double]] = {
        var tab = tableau
        val pivotValue = tab(pivotRowInd)(pivotColInd)
        // printTableauRow(tab, pivotRowInd)
        tab(pivotRowInd) = tab(pivotRowInd).map(v => v / pivotValue )         
        for(r <- 0 to tab.length - 1){
            if(r != pivotRowInd){
                val subRow = tab(pivotRowInd).map(v => v * tab(r)(pivotColInd))
                tab(r) = tab(r).zip(subRow).map { case (x, y) => x - y }
            }   
        }
        
        tab
    }

    def checkStoppage(tableau: Array[Array[Double]]): Boolean = {
        if(tableau.last.filter(_ < 0).length == 0 ) false else true
    }
    
    def solveSimplex(tableau: Array[Array[Double]]): Array[Array[Double]] = {
        var tab = tableau
        var r = 0
        var c = 0
        printTableau(tab)
        var stop = 0
        while(checkStoppage(tab) && stop < 500){
            c = findPivotColumnIndex(tab)
            r = findPivotRowIndex(tab, c)

            println("pivot col " + c)
            println("pivot row " + r)
            
            tab = pivot(tab, c, r)
            printTableau(tab)
            stop = stop + 1
        }
        tab
    }

    def printTableau(tableau: Array[Array[Double]]): Unit = {
        print(tableau.map(_.mkString(", ")).mkString("\n") + "\n\n")
    }
    def printTableauRow(tableau: Array[Array[Double]], r: Int): Unit = {
        print(tableau(r).map(_.toString).mkString(", ") + "\n\n")
    }
}
