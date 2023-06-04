const x_input = document.querySelector("#x-input-holder input")
const y_input = document.querySelector("#y-input-holder input")
const r_input = document.querySelector("#r-input-holder input")

function parse_x_value() {
    let parsed_x = parseFloat(x_input.value)
    if (!isNaN(parsed_x)) {
        if ((parsed_x >= -5) && (parsed_x <= 5)) return parsed_x;
    }
    return NaN;
}

function parse_y_value() {
    let parsed_y = parseFloat(y_input.value)
    if (!isNaN(parsed_y)) {
        if ((parsed_y >= -3) && (parsed_y <= 5)) return parsed_y;
    }
    return NaN;
}

function parse_r_value() {
    let parsed_r = parseFloat(r_input.value)
    if (!isNaN(parsed_r)) {
        if ((parsed_r >= -5) && (parsed_r <= 5)) return parsed_r;
    }
    return NaN;
}