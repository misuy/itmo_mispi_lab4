const ok_button = document.querySelector("#ok-button-holder button")

function handle_graph_click(event) {
    let x_pos = event.clientX + window.scrollX;
    let y_pos = event.clientY + window.scrollY;
    let element = document.getElementById("graph-holder");
    let box = element.getBoundingClientRect()
    let x_middle = element.clientWidth / 2 + box.left + window.scrollX;
    let y_middle = element.clientHeight / 2 + box.top + window.scrollY;

    let r_pixels = getR()
    let r = parse_r_value();
    if (isNaN(r)) alert("please define r")
    else {
        let x = (x_pos - x_middle) * r / r_pixels
        let y = (y_middle - y_pos) * r / r_pixels
        x_input.value = x
        y_input.value = y
        ok_button.click()
    }
}