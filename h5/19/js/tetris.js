/**
 * Created by young on 2017/2/17.
 */
// 定义一个创建canvas组件的函数
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
    for(var i = 1; i < TETRIS_ROWS;i++){
        tetris_ctx.moveTo(0 , i * CELL_SIZE);
        tetris_ctx.lineTo(TETRIS_COLS * CELL_SIZE , i * CELL_SIZE);
    }
}