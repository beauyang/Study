/**
 * Created by young on 2017/2/17.
 */
// 定义一个创建canvas组件的函数
/**
 *
 * @param rows  行
 * @param cols  列
 * @param cellWidth  单元格宽
 * @param cellHeight 单元格高
 */
var createCanvas = function (TETRIS_ROWS, TETRIS_COLS, CELL_SIZE, CELL_SIZE) {

    tetris_canvas = document.createElement("canvas");
    // 设置canvas组件的高度。宽度
    tetris_canvas.width = TETRIS_ROWS * CELL_SIZE;
    tetris_canvas.height = TETRIS_COLS * CELL_SIZE;
    // 设置canvas组建的边框
    tetris_canvas.style.border = "1px solid black";
    // 获取canvas上的绘图API
    tetris_ctx = tetris_canvas.getContext("2d");
    // 绘制横向网格对应的路径
    for (var i = 1; i < TETRIS_ROWS; i++) {
        tetris_ctx.moveTo(0, i * CELL_SIZE);
        tetris_ctx.lineTo(TETRIS_COLS * CELL_SIZE, i * CELL_SIZE);
    }
    for (var i = 1; i < TETRIS_COLS; i++) {
        tetris_ctx.moveTo(i * CELL_SIZE, 0);
        tetris_ctx.lineTo(i * CELL_SIZE, TETRIS_ROWS * CELL_SIZE);
    }
    tetris_ctx.closePath();
    // 设置笔触颜色
    tetris_ctx.strokeStyle = "#aaa";
    // 设置线条粗细
    tetris_ctx.lineWidth = 0.3;
    // 绘制线条
    tetris_ctx.stroke();

    // 定义几种可能出现的方块组合
    var blockArr = [
        // 代表第一种可能出现的方块组合：Z
        [
            {x: TETRIS_COLS / 2 - 1, y: 0, color: 1},
            {x: TETRIS_COLS / 2, y: 0, color: 1},
            {x: TETRIS_COLS / 2, y: 1, color: 1},
            {x: TETRIS_COLS / 2 + 1, y: 1, color: 1}
        ],
        // 代表第二种可能出现的方块组合：反向Z
        [
            {x: TETRIS_COLS / 2 + 1, y: 0, color: 2},
            {x: TETRIS_COLS / 2, y: 0, color: 2},
            {x: TETRIS_COLS / 2, y: 1, color: 2},
            {x: TETRIS_COLS / 2 - 1, y: 1, color: 2}
        ],
        // 代表第三种可能出现的方块组合：田
        [
            {x: TETRIS_COLS / 2 - 1, y: 0, color: 3},
            {x: TETRIS_COLS / 2, y: 0, color: 3},
            {x: TETRIS_COLS / 2 - 1, y: 1, color: 3},
            {x: TETRIS_COLS / 2, y: 1, color: 3}
        ],
        //代表第四种可能出现的方块组合 : L
        [
            {x: TETRIS_COLS / 2 - 1, y: 0, color: 4},
            {x: TETRIS_COLS / 2 - 1, y: 1, color: 4},
            {x: TETRIS_COLS / 2 - 1, y: 2, color: 4},
            {x: TETRIS_COLS / 2, y: 2, color: 4}
        ],
        //代表第五种可能出现的方块组合 : J
        [
            {x: TETRIS_COLS / 2, y: 0, color: 5},
            {x: TETRIS_COLS / 2, y: 1, color: 5},
            {x: TETRIS_COLS / 2, y: 2, color: 5},
            {x: TETRIS_COLS / 2 - 1, y: 2, color: 5}
        ],
        //代表第六种可能出现的方块组合 : I
        [
            {x: TETRIS_COLS / 2, y: 0, color: 6},
            {x: TETRIS_COLS / 2, y: 1, color: 6},
            {x: TETRIS_COLS / 2, y: 2, color: 6},
            {x: TETRIS_COLS / 2, y: 3, color: 6}
        ],
        //代表第七种可能出现的方块组合 :
        [
            {x: TETRIS_COLS / 2, y: 0, color: 7},
            {x: TETRIS_COLS / 2 - 1, y: 1, color: 7},
            {x: TETRIS_COLS / 2, y: 1, color: 7},
            {x: TETRIS_COLS / 2, y: 1, color: 7}
        ],
    ];
    //该数组用于记录底下已经固定的方块
    var tetris_status = [];
    for (var i = 0; i < TETRIS_COLS; i++) {
        tetris_status[i] = [];
        for (var j = 0; j < TETRIS_COLS; j++) {
            tetris_status[i][j] = NO_BLOCK;
        }
    }
    // 定义初始化正在掉落的方块
    var initBlock = function () {
        var rand = Math.floor(Math.random() * blockArr.length);
        // 随机生成掉落的方块
        var currentFall = [
            {x: blockArr[rand][0].x, y: blockArr[rand][0].y, color: blockArr[rand][0].color},
            {x: blockArr[rand][1].x, y: blockArr[rand][1].y, color: blockArr[rand][1].color},
            {x: blockArr[rand][2].x, y: blockArr[rand][2].y, color: blockArr[rand][2].color},
            {x: blockArr[rand][3].x, y: blockArr[rand][3].y, color: blockArr[rand][3].color}

        ];
        // 控制方块向下掉
        var moveDown = function () {
            // 定义是否能够掉落的旗帜
            var canDown = true;
            // 遍历每一个方块，判断是否能向下掉落
            for (var i = 0; i < currentFall.length; i++){
                // 判断是否已经到“最底下”
                if (currentFall[i].y >=TETRIS_ROWS - 1){
                    canDown = false;
                    break;
                }
                // 判断下一格是否“有方块”,如果下一格有方块，则不能向下掉落
                if (tetris_status[currentFall[i].y + 1][currentFall[i].x] !=NO_BLOCK){
                    canDown = false;
                    break;
                }
            }
            // 如果能向下“掉落”
            if (canDown){
                // 将下移前的每个方块的背景色涂成白色
                for (var i = 0; i < currentFall.length; i++){
                    var cur = currentFall[i];
                    // 设置填充颜色
                    tetris_ctx.fillStyle = 'white';
                    // 绘制矩形
                    tetris_ctx.fillRect(cur.x * CELL_SIZE + 1 , cur.y * CELL_SIZE + 1, CELL_SIZE -2 , CELL_SIZE -2);
                    // 遍历每个方块，控制每个方块的y坐标加1
                    // 也就是控制方块都掉落一格
                    for (var i = 0 ; i < currentFall.length ; i++){
                        var cur = currentFall[i];
                        cur.y ++;
                    }
                    // 将下移后的每个方块的背景色涂成该方块的颜色值
                    for (var i = 0; i < currentFall.length; i++){
                        var cur = currentFall[i];
                        // 设置填充颜色
                        tetris_ctx.fillStyle = colors[cur.color];
                        // 绘制矩形
                        tetris_ctx.fillRect(cur.x * CELL_SIZE + 1, cur.y * CELL_SIZE + 1  , CELL_SIZE -2 , CELL_SIZE - 2);
                    }
                }
            }

        }
    };


};