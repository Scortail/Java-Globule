package com.projet.Cell;

import com.projet.WhiteCell.*;
import com.projet.RedCell.*;

public interface CellFactory {
    public TCell createTCell();

    public BCell createBCell();

    public RedBloodCell createRedBloodCell();
}