const stage = acgraph.create("clock-holder")

function draw_clocks_line(angle, box, radius, path, size) {
    let dot_x = box/2 + radius * Math.sin(angle)
    let dot_y = box/2 - radius * Math.cos(angle)
    let dot2_x = box/2 + (radius - size) * Math.sin(angle)
    let dot2_y = box/2 - (radius - size) * Math.cos(angle)
    path.moveTo(dot_x, dot_y)
    path.lineTo(dot2_x, dot2_y)
}

function draw_seconds_arrow(seconds, box, path, size) {
    let angle = (seconds / 60) * 2 * Math.PI
    let arrow_end_x = box/2 + size * Math.sin(angle)
    let arrow_end_y = box/2 - size * Math.cos(angle)
    path.moveTo(box/2, box/2)
    path.lineTo(arrow_end_x, arrow_end_y)
}

function draw_minutes_arrow(minutes, box, path, size) {
    let angle = (minutes / 60) * 2 * Math.PI
    let arrow_end_x = box/2 + size * Math.sin(angle)
    let arrow_end_y = box/2 - size * Math.cos(angle)
    path.moveTo(box/2, box/2)
    path.lineTo(arrow_end_x, arrow_end_y)
}

function draw_hours_arrow(hours, box, path, size) {
    let angle = ((hours % 12) / 12) * 2 * Math.PI
    let arrow_end_x = box/2 + size * Math.sin(angle)
    let arrow_end_y = box/2 - size * Math.cos(angle)
    path.moveTo(box/2, box/2)
    path.lineTo(arrow_end_x, arrow_end_y)
}

function draw_clocks() {
    let holder = document.getElementById("clock-holder")
    stage.removeChildren()
    let date = new Date()
    let width = holder.clientWidth
    let height = holder.clientHeight
    let offset = 10
    let box = Math.min(width, height)
    let radius = box/2 - offset
    let path = stage.path()

    //drawing clocks
    path.moveTo(box-offset, box/2)
    path.arcTo(radius, radius, 0, 360)

    for (let i = 0; i<=2*Math.PI; i+=Math.PI/6) {
        draw_clocks_line(i, box, radius, path, 10)
    }

    draw_seconds_arrow(date.getSeconds(), box, path, box/2 - 20)
    draw_minutes_arrow(date.getMinutes(), box, path, box/3)
    draw_hours_arrow(date.getHours(), box, path, box/4)

    path.stroke("black", 2)
}

window.onload = function () {
    draw_clocks()
    setInterval(draw_clocks, 1000)
}