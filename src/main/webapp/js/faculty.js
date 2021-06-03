const inputs = document.querySelectorAll('.getId');
const clickHandler = i => {
    document.getElementById("facultyUpdate").value = i.getAttribute("value");
    document.getElementById("facultyDelete").value = i.getAttribute("value")
    document.getElementById("facultyGetById").value = i.getAttribute("value");
};
inputs.forEach(i => i.onclick = () => clickHandler(i));

function facultyUpdate() {
    let facultyToUpdate = document.getElementById('facultyUpdate');
    if (facultyToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('updateForm').submit();
    }
}

function facultyDelete() {
    let facultyToUpdate = document.getElementById('facultyDelete');
    if (facultyToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('deleteForm').submit();
    }
}

function getFacultyById() {
    let facultyToUpdate = document.getElementById('facultyGetById');
    if (facultyToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('getByIdForm').submit();
    }
}