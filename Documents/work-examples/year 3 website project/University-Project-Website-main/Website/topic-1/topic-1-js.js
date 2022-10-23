var i = 7.8; // used within #run-button function
/**
 * handling the auto run-button's automation and speed when clicked.
 * when the run button is clicked the position of the slider is checked
 * the corresponding if block will run with a setInterval 'loop',
 * incrementing i and also updating the animation on each iteration.
 * once the slider and animation have reached a point where a new speed
 * is required the interval is cleared using its ID which was created upon
 * entering the original if block (I.E line 15-16, etc) and a new
 * setInterval is started with the required speed as the smallness of the
 * setInterval delay, unless, the animation and slider are at their max values
 * in which case i is reset to 7.8
 */
$(document).ready(function () {
  // when the button with id run-button is clicked this function is called
  $("#run-button").on("click", function () {
    if (i < 28.9) {
      // new variable created here to store setInterval id 
      var intervalID = setInterval(function () {
        console.log("check");
        i++;

        updateProcessLocation(i);
        updateActiveThread(i);
        updateText(i);
        document.getElementById("process-state-slider").value = i;
        // upon reaching the required amount, interval will be cleared and function is called again to reenter the if else if statement
        if (i > 28.9) {
          clearInterval(intervalID);
          $("#run-button").click();
        }
      }, 300);
    } else if (i > 28.9 && i < 34.7) {
      var intervalID2 = setInterval(function () {
        console.log("check");
        i++;

        updateProcessLocation(i);
        updateActiveThread(i);
        updateText(i);
        document.getElementById("process-state-slider").value = i;

        if (i > 34.7) {
          clearInterval(intervalID2);
          $("#run-button").click();
        }
      }, 1000);
    } else if (i > 34.7 && i < 56.7) {
      var intervalID3 = setInterval(function () {
        console.log("check");
        i++;

        updateProcessLocation(i);
        updateActiveThread(i);
        updateText(i);
        document.getElementById("process-state-slider").value = i;

        if (i > 56.7) {
          clearInterval(intervalID3);
          $("#run-button").click();
        }
      }, 300);
    } else if (i > 56.7 && i < 60.7) {
      var intervalID4 = setInterval(function () {
        console.log("check");
        i++;

        updateProcessLocation(i);
        updateActiveThread(i);
        updateText(i);
        document.getElementById("process-state-slider").value = i;

        if (i > 60.7) {
          clearInterval(intervalID4);
          $("#run-button").click();
        }
      }, 1000);
    } else if (i > 60.7 && i < 87.7) {
      var intervalID5 = setInterval(function () {
        console.log("check");
        i++;

        updateProcessLocation(i);
        updateActiveThread(i);
        updateText(i);
        document.getElementById("process-state-slider").value = i;
        /*  upon reaching the end of the process reset i value instead of calling function again so that i is ready to be changed 
        back to the start on the next click of the run button */
        if (i > 87.7) {
          clearInterval(intervalID5);
          i = 7.8;
        }
      }, 150);
    }
  });

  /**
   * takes the current value of the slider and calls the update function with
   * value as a parameter
   */
  $("#process-state-slider").on("input", function () {
    // upon a change to the slider value, call this function
    console.log("input");
    var position = $("#process-state-slider").val();
    updateProcessLocation(position);
    updateActiveThread(position);
    updateText(position);
  });

  /**
   * apprently IE 10 doesnt accept oninput but does accept onchange
   */
  $("#process-state-slider").on("change", function () {
    // ie version of the function above, doesnt update until the slider is droped into a place by the user
    console.log("change");
    var position = $("#process-state-slider").val();
    updateProcessLocation(position);
    updateActiveThread(position);
    updateText(position);
  });
});

/**
 * checks where the process is at and changes the thread boxes characteristics.
 * starts by settings the process thread runtime boxes to match the slider.
 * check the position of the slider and changes the characteristics of a single
 * thread box at a time.
 *
 * @param {int} position - current position of process runtime
 */
function updateProcessLocation(position) {
  console.log(position);
  var positionVW = position + "vw";
  document.getElementById("process-top-thread").setAttribute("x1", positionVW);
  document.getElementById("process-top-thread").setAttribute("x2", positionVW);

  document.getElementById("process-bot-thread").setAttribute("x1", positionVW);
  document.getElementById("process-bot-thread").setAttribute("x2", positionVW);

  // first thread excution
  if (position > 8.7 && position < 29.9) {
    console.log(document.getElementById("executing-first").style.display);

    document.getElementById("thread-box-topr").style.fill = "rgba(255, 255, 255, 0.5)";
    document.getElementById("executing-first").style.display = "unset";
  } else if (position > 29.9) {
    document.getElementById("thread-box-topr").style.fill = "rgba(0, 0, 170, 0.5)";
    document.getElementById("executing-first").style.display = "none";
  } else {
    document.getElementById("executing-first").style.display = "none";
  }

  // second thread excution
  if (position > 34.7 && position < 56.7) {
    console.log(document.getElementById("executing-second").style.display);

    document.getElementById("thread-box-botc").style.fill = "rgba(255, 255, 255, 0.5)";
    document.getElementById("executing-second").style.display = "unset";
  } else if (position > 56.7) {
    document.getElementById("thread-box-botc").style.fill = "rgba(0, 0, 170, 0.5)";
    document.getElementById("executing-second").style.display = "none";
  } else {
    document.getElementById("executing-second").style.display = "none";
  }

  // return tofirst thread excution
  if (position > 60.7 && position < 87.7) {
    console.log(document.getElementById("executing-third").style.display);

    document.getElementById("thread-box-topl").style.fill = "rgba(255, 255, 255, 0.5)";
    document.getElementById("executing-third").style.display = "unset";
  } else if (position > 87.7) {
    document.getElementById("thread-box-topl").style.fill = "rgba(0, 0, 170, 0.5)";
    document.getElementById("executing-third").style.display = "none";
  } else {
    document.getElementById("executing-third").style.display = "none";
  }
}

/**
 * checks where the process is at and changes color of both process thread runtime boxes.
 *
 * @param {int} position - current position of process runtime
 */
function updateActiveThread(position) {
  if (position > 32 && position < 58) {
    document.getElementById("process-top-thread").style.stroke = "rgba(71, 58, 58, 0.63)";
    document.getElementById("process-bot-thread").style.stroke =
      "rgba(32, 160, 28, 0.63)";
  } else if (position < 32 || position > 58) {
    document.getElementById("process-top-thread").style.stroke = "rgba(32, 160, 28, 0.63)";
    document.getElementById("process-bot-thread").style.stroke = "rgba(71, 58, 58, 0.63)";
  }
}

/**
 * updates the text of main-information depending on process runtime
 * @param {int} position - current position of process runtime
 */
function updateText(position) {
  if (position > 8.7 && position < 29.9) {
    document.getElementById("main-information").innerHTML = "The process starts executing the first thread";
  } else if ((position > 29.9 && position < 34.7) ||(position > 56.7 && position < 60.7)) {
    document.getElementById("main-information").innerHTML = "The process switches thread, incurring an overhead of 'inactivity' while it changes the threads states";
  } else if (position > 34.7 && position < 56.7) {
    document.getElementById("main-information").innerHTML = "The process executes the second threads";
  } else if (position > 56.7) {
    document.getElementById("main-information").innerHTML = "The process switches back to the first thread to finish its execution";
  }
}
