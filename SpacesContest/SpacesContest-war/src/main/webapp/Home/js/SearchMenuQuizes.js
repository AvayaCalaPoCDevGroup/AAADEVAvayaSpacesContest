function myFunctionSearch() {
  // Declare variables
  var input, filter, ul, li, a, i;
  input = document.getElementById("mySearch");
  filter = input.value.toUpperCase();
  ul = document.getElementById("myMenu");
  li = document.getElementsByClassName('searchOption');

  let quizesGroup = document.getElementById('quizesGroup').children;
  console.log(quizesGroup);
  for (let index = 0; index < quizesGroup.length; index++) {
    var element = quizesGroup[index].children[1].children[0].children[0].children[0].innerHTML;
    console.log(element);
    if (element.toUpperCase().indexOf(filter) > -1) {
      quizesGroup[index].style.display = "";
    } else {
      quizesGroup[index].style.display = "none";
    }
  }

  // Loop through all list items, and hide those who don't match the search query
  for (i = 0; i < li.length; i++) {
    a = li[i];
    if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }
}