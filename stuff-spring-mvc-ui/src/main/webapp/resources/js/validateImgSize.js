const _URL = window.URL;

const view = $("#output");
const imgName = $("#imgName");
const imgInput = $("#picture");
const imgInputErrors = $("#pictureErrors");
const resetButton = $("#resetImg");

const MAX_EDGE_LENGTH = 300;


const isFileNotImage = (file) => {
    const validFileResolutions = ["jpeg", "jpg", "png", "gif"];
    let fileResolution = file.type.split('/').pop().toLowerCase();
    return !validFileResolutions.includes(fileResolution);
}

const isEdgeNotValid = (edgeLength) => edgeLength > MAX_EDGE_LENGTH;

const cleanView = () => {
    view.attr("src", "../resources/img/no-image-available.png");
    imgName.text("Choose image file");
    imgInputErrors.text("");
};

const printEdgeNotValid = (edgeName, edgeLength) => {
    imgInputErrors.text(
        `Image ${edgeName} is ${edgeLength}px, but should not be more than ${MAX_EDGE_LENGTH}px.`
    );
};

imgInput.change(function (e) {
    cleanView();
    const file = this.files[0];
    if (file) {
        let img = new Image();
        img.onload = function () {
            if (file.size > 1024000) {
                imgInput.val("");
                imgInputErrors.text("Max Upload size is 1MB only.");
            } else if(isFileNotImage(file)) {
                imgInput.val("");
                imgInputErrors.text("File should have one of those resolutions (jpeg, jpg, png, gif).");
            } else if (isEdgeNotValid(this.width)) {
                imgInput.val("");
                printEdgeNotValid("width", this.width);
            } else if (isEdgeNotValid(this.height)) {
                imgInput.val("");
                printEdgeNotValid("height", this.height);
            } else {
                imgName.text(file.name);
                view.attr("src", _URL.createObjectURL(file));
            }
        };
        img.src = _URL.createObjectURL(file);
    }
});

resetButton.on('click', function (e) {
    imgInput.val("");
    cleanView();
})