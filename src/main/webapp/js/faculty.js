const inputs = document.querySelectorAll('.getId');
const clickHandler = i => {
    document.getElementById("update").value = i.getAttribute("value");
    document.getElementById("delete").value = i.getAttribute("value");
};
inputs.forEach(i => i.onclick = () => clickHandler(i));

function updateFaculty() {
    let facultyToUpdate = document.getElementById('update');
    if (facultyToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('updateForm').submit();
    }
}

function deleteFaculty() {
    let facultyToUpdate = document.getElementById('delete');
    if (facultyToUpdate.value === '') {
        // modalAlert();
        return false;
    } else {
        document.getElementById('deleteForm').submit();
    }
}

function facultiesSortDash(linkObj) {
    if (linkObj.id === 'nameSort') {
        linkObj.href = '${pageContext.request.contextPath}/controller?command=facultiesPage&sortedType=nameAsc';
    }
    if (linkObj.id === 'budgetSort') {
        linkObj.href = '${pageContext.request.contextPath}/controller?command=facultiesPage&sortedType=budgetAsc';
    }
    if (linkObj.id === 'totalSort') {
        linkObj.href = '${pageContext.request.contextPath}/controller?command=facultiesPage&sortedType=totalAsc';
    }
}