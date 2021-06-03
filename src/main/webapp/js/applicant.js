const inputs = document.querySelectorAll('.getId');
const clickHandler = i => {
    document.getElementById("applicantDelete").value = i.getAttribute("value");
    document.getElementById("getApplicantById").value = i.getAttribute("value");
};
inputs.forEach(i => i.onclick = () => clickHandler(i));

const status = document.querySelectorAll('.getStatus');
const updateHandler = i => {
    document.getElementById("applicantUpdate").value = i.getAttribute("value");
};
status.forEach(i => i.onclick = () => updateHandler(i));

function getCertificate(buttonCertificate) {
    let div = document.getElementById('certificate');
    if (div.style.display !== 'block') {
        div.style.display = 'block';
        buttonCertificate.innerHTML = '<fmt:message key="applicant.HideCertificate"/>';
    } else {
        div.style.display = 'none';
        buttonCertificate.innerHTML = '<fmt:message key="applicant.DisplayCertificate"/>';
    }
}

function applicantUpdate() {
    let applicantToUpdate = document.getElementById('applicantUpdate');
    if (applicantToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('updateForm').submit();
    }
}

function applicantDelete() {
    let facultyToUpdate = document.getElementById('applicantDelete');
    if (facultyToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('deleteForm').submit();
    }
}

function applicantGetById() {
    let facultyToUpdate = document.getElementById('getApplicantById');
    if (facultyToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('getByIdForm').submit();
    }
}