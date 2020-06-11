function w3_open() {
    document.getElementById("main").style.marginLeft = "28%";
    document.getElementById("mySidebar").style.width = "28%";
    document.getElementById("mySidebar").style.display = "block";

    var w3Container = document.getElementsByClassName('w3-container');
    console.log(w3Container);
    for (let index = 0; index < w3Container.length; index++) {
        w3Container[index].style.marginLeft = "400px";
        
    }
  }
  function w3_close() {
    document.getElementById("main").style.marginLeft = "0%";
    document.getElementById("mySidebar").style.display = "none";
    var w3Container = document.getElementsByClassName('w3-container');
    for (let index = 0; index < w3Container.length; index++) {
        w3Container[index].style.marginLeft = "0px";
        
    }
  }

  function toogledSideMenuQuizes(){
      var mySidebar = document.getElementById('mySidebar');
      if(mySidebar.style.display === "block"){
        w3_close();
      }else{
        w3_open();
      }
  }