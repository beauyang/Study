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
var createCanvas = function(rows , cols , cellWidth , cellHeight){

    tetris_canvas = document.createElement("canvas");
    // 设置canvas组件的高度。宽度
    tetris_canvas.width = cols * cellWidth;
    tetris_canvas.height = cols * cellHeight;
    // 设置canvas组建的边框
    tetris_canvas.style.border = "1px solid black";
    // 获取canvas上的绘图API
    tetris_ctx = tetris_canvas.getContext("2d");
    // 绘制横向网格对应的路径
    for(var i = 1; i < TETRIS_ROWS; i++){
        tetris_ctx.moveTo(0 , i * CELL_SIZE);
        tetris_ctx.lineTo(TETRIS_COLS * CELL_SIZE , i * CELL_SIZE);
    }
    for (var i = 1; i < TETRIS_COLS; i++){
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
            {x : TETRIS_COLS / 2 - 1 , y : 0 , color : 1 },
            {x : TETRIS_COLS / 2 , y : 0 , color : 1 },
            {x : TETRIS_COLS / 2 , y : 1 , color : 1 },
            {x : TETRIS_COLS / 2 + 1 , y : 1 , color : 1 }
        ]
    ]

    // 记录四种方块下落的位置
    var currentFall = [
        {x : blockArr[rand][0].x , y : blockArr[rand][0].y , color : blockArr[rand][0].color},
        {x : blockArr[rand][1].x , y : blockArr[rand][1].y , color : blockArr[rand][1].color},
        {x : blockArr[rand][2].x , y : blockArr[rand][2].y , color : blockArr[rand][2].color},
        {x : blockArr[rand][3].x , y : blockArr[rand][3].y , color : blockArr[rand][3].color}

    ];
    //该数组用于记录底下已经固定的方块
    var tetris_status = [];
    for (var i = 0; i < TETRIS_COLS; i++){
        tetris_status[i] = [];
        for (var j = 0; j < TETRIS_COLS; j++){
            tetris_status[i][j] = NO_BLOCK;
        }
    }
}