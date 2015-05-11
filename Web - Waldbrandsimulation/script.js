var wood = [];
var interval;

function  getDensity() {
    return document.getElementById('density').value / 100;
}

function  getHumidity() {
    return document.getElementById('humidity').value / 100;
}

function  getSpeed() {
    return parseInt(document.getElementById('speed').value) * 100;
}

function  getColumns() {
    return 100;
}

function  getRows() {
    return 100;
}

function initScenery() {
    stopInterval();
    wood = growWood(getColumns(), getRows(), getDensity());
    drawWood();
}

function stopInterval() {
    if (interval) {
        clearInterval(interval);
    }
}

function growWood(columns, rows, density) {
    var array = [];
    for (var x = 0; x < columns; x++) {
        array[x] = [rows];
        for (var y = 0; y < rows; y++) {
            array[x][y] = (Math.random() < density) ? 100 : -1;
        }
    }
    return array;
}


function initCanvasContext() {
    var canvas = document.getElementById('canvas');
    var context = canvas.getContext('2d');

    var clickListener = function(evt) {
        var mousePos = getMousePos(canvas, evt);
        ignite(mousePos);
    };
    context.canvas.addEventListener('mousedown', clickListener, true);
    return context;
}

function getMousePos(canvas, evt) {
    var rect = canvas.getBoundingClientRect();
    return {
        x: evt.clientX - rect.left,
        y: evt.clientY - rect.top
    };
}

function ignite(mousePos) {
    var x = parseInt(mousePos.x / 5);
    var y = parseInt(mousePos.y / 5);
    if (isWood(x, y)) {
        
        var canvas = document.getElementById('canvas');
        var context = canvas.getContext('2d');
        interval = setInterval(function() {
            burn(x, y, getHumidity());
            drawWood();
        }, getSpeed());
    }
}

function isWood(x, y) {

    return wood[x][y] > 0;
}

function drawWood() {
    var context = initCanvasContext();
    for (var x = wood.length - 1; x >= 0; x--) {
        for (var y = wood.length - 1; y >= 0; y--) {
            if (wood[x][y] <= -1) {
                context.fillStyle = "lightgreen";
            } else if (wood[x][y] >= 100) {
                context.fillStyle = "darkgreen";
            } else if (wood[x][y] >= 90 && wood[x][y] < 100) {
                context.fillStyle = "yellow";
            } else if (wood[x][y] >=50 && wood[x][y] < 90) {
                context.fillStyle = "orange";
            } else if (wood[x][y] > 0 && wood[x][y] < 50) {
                context.fillStyle = "red";
            } else if (wood[x][y] == 0) {
                context.fillStyle = "black";
            }
            context.fillRect(5 * x, 5 * y, 5, 5);
        }
    }
    countTrees();
}

function countTrees() {
    var lebend = 0;
    var brennend = 0;
    var verbrannt = 0;

    for (var x = 0; x < getRows()-1; x++) {
        for (var y = 0; y < getColumns()-1; y++) {
            if (wood[x][y] == 100) {
                lebend++;
            } else if (wood[x][y] > 0 && wood[x][y] < 100) {
                brennend++;
            } else if (wood[x][y] == 0) {
                verbrannt++;
            }
        }
    }
    setTreeCount(lebend, brennend, verbrannt);
}

function setTreeCount(lebend, brennend, verbrannt) {
    var treeCount = document.getElementById('treeCount');
    treeCount.innerHTML = "Bäume: " + (lebend + brennend + verbrannt) + "<br>" + lebend + " lebend, " + "<br>" + brennend + " brennend, " + "<br>" + verbrannt + " verbrannt.";

}

function isBurning(x, y) {
    return wood[x][y] <= 99 && wood[x][y] > 0;
}

function isBurnt(x, y) {
    return wood[x][y] == 0;
}

function hasBurningNeighbour(x, y) {
    //console.log(wood[x][y]);
    if (x > 0 && y > 0 && x < getRows() - 1 && y < getColumns() - 1) {
        return isBurning(x - 1, y + 1) || isBurning(x + 1, y - 1) || isBurning(x - 1, y - 1) || isBurning(x + 1, y + 1) ||
                isBurning(x - 1, y) || isBurning(x, y - 1) || isBurning(x + 1, y) || isBurning(x, y + 1);
    }
    else if (x > 0 && y > 0) {
        return isBurning(x - 1, y - 1) || isBurning(x - 1, y) || isBurning(x, y - 1);
    }
    else if (x < getRows() - 1 && y < getColumns() - 1) {
        return isBurning(x + 1, y + 1) || isBurning(x + 1, y) || isBurning(x, y + 1);
    }
    else if (x > 0) {
        return isBurning(x - 1, y);
    }
    else if (y > 0) {
        return isBurning(x, y - 1);
    }
    else if (x < getRows() - 1) {
        return isBurning(x + 1, y);
    }
    else if (y < getColumns() - 1) {
        return isBurning(x, y + 1);
    }
    return false;
}


function burn(x, y, humidity) {
    var burnfactor = 1;
    wood[x][y] -= 1;
    var updatedWood = [];
    var columns = getColumns();
    var rows = getRows();
    for (var x = 0; x < columns; x++) {
        updatedWood[x] = [rows];
        for (var y = 0; y < rows; y++) {
            if ((isBurning(x, y) || hasBurningNeighbour(x, y)) && !isBurnt(x, y)) {
                updatedWood[x][y] = (Math.random() < humidity) ? wood[x][y] : (wood[x][y] - burnfactor);
                if (x == 10 && y == 10) {
                    console.log("bei 10|10: " + updatedWood[10][10]);
                }
            } else {
                updatedWood[x][y] = wood[x][y];
            }
        }
    }
    wood = updatedWood;
}


