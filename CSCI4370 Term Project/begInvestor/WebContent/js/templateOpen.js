var callOne = true;

function onecli() {
   	document.getElementById("side-navagate-bar").classList.toggle('active');
	document.getElementById("main-area").classList.toggle('active');
	document.getElementById("main-area").style.width = "100%";
}

function twocli() {
	document.getElementById("side-navagate-bar").classList.toggle('active');
	document.getElementById("main-area").classList.toggle('active');
   	document.getElementById("main-area").style.width = "calc(100%-200px)";
}

function toggleSideBar(){
   	if(callOne){
   		onecli();
   	}else{
   		twocli();
   	}
  	callOne = !callOne;
}

function main(){
	document.getElementById("main-area").classList.toggle('active');
}
