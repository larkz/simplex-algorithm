package simplex

object SimplexRun {
    
    def createTableau(nonBasicVariables: Array[Array[Double]]): Array[Array[Double]] = {
        // Enter in tableau directly, in the future create an input portal
        nonBasicVariables
    }
    
    def findPivotColumnIndex(tableau: Array[Array[Double]]): Int = {
        val lastRow = tableau.last
        var colVariable = lastRow(0)
        var colIndex = 0
        lastRow.zipWithIndex.maxBy(_._1)._2
    }

    // Smallest ratio
    def findPivotRowIndex(tableau: Array[Array[Double]], pivotColInd: Int ): Int = {
        val pivotCol = tableau.map(r => r(pivotColInd))
        val constraintCol = tableau.map(r => r.last)
        val ratios = constraintCol.zip(pivotCol).map { case (x, y) => x / y }
        ratios.zipWithIndex.minBy(_._1)._2
    }

    def pivot(tableau: Array[Array[Double]], pivotColInd: Int, pivotRowInd: Int): Array[Array[Double]] = {
        var tab = tableau
        tab(pivotRowInd) = tab(pivotRowInd).map(v => v / tab(pivotRowInd)(pivotColInd) ) 

        for(r <- 0 to tab.length - 1){
            if (tab(r)(pivotColInd) > 0 ) {
                val subRow = tab(pivotRowInd).map(v => v * tab(r)(pivotColInd))
                tab(r) = tab(r).zip(subRow).map { case (x, y) => x - y }
            } else if (tab(r)(pivotColInd) < 0 ) {
                val plusRow = tab(pivotRowInd).map(v => v * tab(r)(pivotColInd))
                tab(r) = tab(r).zip(plusRow).map { case (x, y) => x + y }
            }
        }
        tab
    }

    def checkStoppage(tableau: Array[Array[Double]]): Boolean = {
        if(tableau.last.filter(_ >= 0).length >= tableau.last.length ) true else false
    }
    

    def solveSimplex(tableau: Array[Array[Double]]): Array[Array[Double]] = {
        
        var tab = tableau
        var r = 0
        var c = 0
        while(checkStoppage(tab)){
            r = findPivotColumnIndex(tab)
            println(r)
            c = findPivotRowIndex(tab, r)
            println(c)
            tab = pivot(tab, r, c)
            printTableau(tab)
        }
        tab
    }

    def printTableau(tableau: Array[Array[Double]]): Unit = {
        print(tableau.map(_.mkString(", ")).mkString("\n") + "\n")
    }
}
