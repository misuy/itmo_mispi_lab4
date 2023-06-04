const firstColor = "black"
const secondColor = "rgba(33, 150, 243, 0.7)"

const stage = acgraph.create("graph-holder")

const attempts = []

class Attempt {
    constructor(x, y, r, result) {
        this.x = x
        this.y = y
        this.r = r
        this.result = result
    }
}

function drawCoordinates(stage, width, height, xMiddle, yMiddle, r) {
    let linePath = stage.path()
    let textStyle = {fontSize: Math.round(r / 13), fontFamily: "Roboto", color: firstColor}

    //drawing vertical arrow
    linePath.moveTo(xMiddle, 0)
    linePath.lineTo(xMiddle, height)
    linePath.moveTo(xMiddle - r / 30, r / 20)
    linePath.lineTo(xMiddle, 0)
    linePath.lineTo(xMiddle + r / 30, r / 20)
    stage.text(xMiddle + r / 30, 0, "Y", textStyle)

    //drawing horizontal arrow
    linePath.moveTo(0, yMiddle)
    linePath.lineTo(width, yMiddle)
    linePath.moveTo(width - r / 20, yMiddle - r / 30)
    linePath.lineTo(width, yMiddle)
    linePath.lineTo(width - r / 20, yMiddle + r / 30)
    stage.text(width - r / 20, yMiddle + r / 30, "X", textStyle)

    //drawing marks
    linePath.moveTo(xMiddle - r / 40, yMiddle - r / 2)
    linePath.lineTo(xMiddle + r / 40, yMiddle - r / 2)
    stage.text(xMiddle + r / 40, yMiddle - r / 2, "R/2", textStyle)

    linePath.moveTo(xMiddle - r / 40, yMiddle - r)
    linePath.lineTo(xMiddle + r / 40, yMiddle - r)
    stage.text(xMiddle + r / 40, yMiddle - r, "R", textStyle)
    linePath.moveTo(xMiddle - r / 40, yMiddle + r / 2)
    linePath.lineTo(xMiddle + r / 40, yMiddle + r / 2)
    stage.text(xMiddle + r / 40, yMiddle + r / 2, "-R/2", textStyle)

    linePath.moveTo(xMiddle - r / 40, yMiddle + r)
    linePath.lineTo(xMiddle + r / 40, yMiddle + r)
    stage.text(xMiddle + r / 40, yMiddle + r, "-R", textStyle)

    linePath.moveTo(xMiddle + r / 2, yMiddle - r / 40)
    linePath.lineTo(xMiddle + r / 2, yMiddle + r / 40)
    stage.text(xMiddle + r / 2, yMiddle + r / 40, "R/2", textStyle)

    linePath.moveTo(xMiddle + r, yMiddle - r / 40)
    linePath.lineTo(xMiddle + r, yMiddle + r / 40)
    stage.text(xMiddle + r, yMiddle + r / 40, "R", textStyle)

    linePath.moveTo(xMiddle - r / 2, yMiddle - r / 40)
    linePath.lineTo(xMiddle - r / 2, yMiddle + r / 40)
    stage.text(xMiddle - r / 2, yMiddle + r / 40, "-R/2", textStyle)

    linePath.moveTo(xMiddle - r, yMiddle - r / 40)
    linePath.lineTo(xMiddle - r, yMiddle + r / 40)
    stage.text(xMiddle - r, yMiddle + r / 40, "-R", textStyle)

    linePath.stroke(firstColor)
}


function drawShape(stage, width, height, xMiddle, yMiddle, r) {
    let linePath = stage.path()

    linePath.moveTo(xMiddle, yMiddle-r/2)
    //drawing arc
    linePath.arcTo(r/2, r/2, 270, 90, r/2)

    //drawing triangle
    linePath.lineTo(xMiddle, yMiddle+r/2)

    //drawing rectangle
    linePath.lineTo(xMiddle-r, yMiddle+r/2)
    linePath.lineTo(xMiddle-r, yMiddle)
    linePath.lineTo(xMiddle, yMiddle)
    linePath.lineTo(xMiddle, yMiddle-r/2)

    linePath.stroke(secondColor)
    linePath.fill(secondColor)
}


function drawAttempt(stage, width, height, xMiddle, yMiddle, r, result) {
    let color;
    if (result.result) color = "rgba(3, 252, 107, 1)"
    else color = "black"
    if (result !== 0) {
        stage.circle(xMiddle + (result.x / result.r) * r, yMiddle - (result.y / result.r) * r, 2).stroke(color).fill(color)
    }
}

function getR() {
    const width = document.getElementById("graph-holder").clientWidth
    const height = document.getElementById("graph-holder").clientHeight
    return Math.min(width, height) * 0.4
}

function plotGraph() {
    stage.removeChildren()
    const width = document.getElementById("graph-holder").clientWidth
    const height = document.getElementById("graph-holder").clientHeight
    const xMiddle = width / 2
    const yMiddle = height / 2
    const r = Math.min(width, height) * 0.4

    drawCoordinates(stage, width, height, xMiddle, yMiddle, r)

    drawShape(stage, width, height, xMiddle, yMiddle, r)

    let focused_r = parse_r_value()

    attempts.forEach(element => {
        if (focused_r === element.r) drawAttempt(stage, width, height, xMiddle, yMiddle, r, element)
    })
}

function loadAttempts() {
    $("table.data-table > tbody").each(function() {
        let table = $(this)
        let rows = table.children()
        rows.each(function () {
            let row = $(this)
            let cells = row.children().children()
            let attempt = new Attempt(parseFloat(cells.get(1).innerHTML), parseFloat(cells.get(2).innerHTML), parseFloat(cells.get(3).innerHTML), cells.get(4).innerHTML === "true")
            attempts.push(attempt)
        })
    })
    return attempts
}


window.onload = function() {
    loadAttempts()
    plotGraph();
    document.getElementById("graph-holder").addEventListener("click", handle_graph_click);
    stage.addEventListener("click", handle_graph_click)
}
window.onresize = plotGraph