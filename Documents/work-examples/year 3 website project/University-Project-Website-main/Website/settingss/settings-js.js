//colorblind options
var oldScheme = "color-schemes/colors.css"; // used within setColorScheme #undo-styles functions
var previousButton = "standard"; // used within colorScheme function
/**
 * sets the color scheme in the iframe, called in the colorScheme method.
 * splits the contents of the srcdoc attribute at the location we want, removing old schemes css file location
 * at the same time. adds new scheme css file location; reassembles contents and sets the iframe to show
 * new settings, saves currently set scheme as oldScheme.
 *
 * @param {String} choosenScheme - the choosen color Schemes css file location, assigned to scheme
 */
function setColorScheme(choosenScheme) {
  var scheme = choosenScheme;

  const viewerSplit = document.getElementById("settings-view").srcdoc.split(oldScheme);

  var viewerFirstHalf = viewerSplit[0] + scheme;
  var viewerSecondHalf = viewerSplit[1];
  var newViewer = viewerFirstHalf + viewerSecondHalf;
  console.log(newViewer);

  document.getElementById("settings-view").srcdoc = newViewer;
  oldScheme = scheme;
}

/**
 * change the color scheme of the iframe depending on what option the user clicked.
 * switch case checks what button was pressed and calls setColorScheme with
 * the correct css file location as a param. sets the selected button to
 * green and resets the last selected option to a pale yellow.
 *
 * @param {String} buttonName - the button pressed, assigned to buttonPressed
 */
function colorScheme(buttonName) {
  var buttonPressed = buttonName;

  switch (buttonPressed) {
    case "standard":
      setColorScheme("color-schemes/colors.css");
      console.log("set to standard");
      break;
    case "protanopia":
      setColorScheme("color-schemes/colors-protanopia.css");
      console.log("set to protanopia");
      break;
    case "protanomaly":
      setColorScheme("color-schemes/colors-protanomaly.css");
      console.log("set to protanomaly");
      break;
    case "deuteranopia":
      setColorScheme("color-schemes/colors-deuteranopia.css");
      console.log("set to deuteranopia");
      break;
    case "deuteranomaly":
      setColorScheme("color-schemes/colors-deuteranomaly.css");
      console.log("set to deuteranomaly");
      break;
    case "tritanopia":
      setColorScheme("color-schemes/colors-tritanopia.css");
      console.log("set to tritanopia");
      break;
    case "tritanomaly":
      setColorScheme("color-schemes/colors-tritanomaly.css");
      console.log("set to tritanomaly");
      break;
  }

  if (previousButton != buttonPressed) {
    document.getElementById(buttonPressed).style = "background-color: rgb(34, 168, 1)";
    document.getElementById(previousButton).style = "background-color: rgb(235, 235, 168)";
    previousButton = buttonPressed;
  }
}

// incoperating jQuery from this point onward
var oldSize = "font-size: 290%;"; // used within #set-text-size #undo-styles functions
var textStyle; // used within #italic-option #bold-option #underlined-option functions
var italic = false; // used within the #italic-option function
var bold = false; // used within the #bold-option function
var underline = false; // used within the #underline function


// jquery function creation
/**
 * creates the new setting and applys it to the iframe.
 * splits the contents of the srcdoc attribute at the location we want, adds new css style;
 * checks if old content needs removed, reassembles contents and sets the iframe to show new settings.
 *
 * @param {String} styling the css style to be implemented
 * @param {String} oldContent the old text size to be replaced
 */
var createNewSettings = function (styling, oldContent) {
  const viewerSplit = document.getElementById("settings-view").srcdoc.split('id="main-information" style="');

  var viewerFirstHalf =viewerSplit[0] +'id="main-information" style="'.concat(" ", "") + styling + ";".concat(" ", "");
  var viewerSecondHalf = viewerSplit[1];

  console.log(viewerSecondHalf.includes(oldContent));
  if (oldContent != null && viewerSecondHalf.includes(oldContent)) {
    viewerSecondHalf = viewerSecondHalf.replace(oldContent + ";", "");
  }

  var newViewer = viewerFirstHalf + viewerSecondHalf;
  console.log(newViewer);

  document.getElementById("settings-view").srcdoc = newViewer;
};

// upon the document/html code beingfully loaded, this function and the functions inside it can be used
$(document).ready(function () {
  /**
   * when set-text-size button is clicked it takes the current value of the text-size textbox
   * creates a css format and passes to createNewSettings along with current oldSize value.
   * Saves size taken from textbox as oldSize value after passing.
   */
  $("#set-text-size").click(function () {
    console.log("clicked set-text-sized");
    // jquery val = value that comes from the id, not the value attribute ( unless the value attribute is the value called for val )
    var size = $("#text-size").val() * 10;
    size = "font-size: " + size + "%";
    createNewSettings(size, oldSize);

    oldSize = size;
  });

  /**
   * when italic-option button is clicked it creates a css format and passes to createNewSettings.
   */
  $("#italic-option").click(function () {
    console.log("clicked italic-option");

    textStyle = "font-style: italic";
    createNewSettings(textStyle, null);
    italic = true;
  });

  /**
   * when bold-option button is clicked it creates a css format and passes to createNewSettings.
   */
  $("#bold-option").click(function () {
    console.log("clicked bold-option");

    textStyle = "font-weight: bold";
    createNewSettings(textStyle, null);
    bold = true;
  });

  /**
   * when underline-option button is clicked it creates a css format and passes to createNewSettings.
   */
  $("#underlined-option").click(function () {
    console.log("clicked underlined-option");

    textStyle = "text-decoration: underline";
    createNewSettings(textStyle, null);
    underline = true;
  });

  /**
   * when undo-styles button is clicked it takes the current iframe srcdoc attribute code,
   * removes inputed options or replaces inputed options with the standard setting,
   *  sets the iframe to show default settings and saves oldScheme and oldSize to
   *  there default forms. resets color scheme button colors, makes undo unclickable.
   */
  $("#undo-styles").click(function () {
    console.log("clicked undo-styles");
    let viewer = document.getElementById("settings-view").srcdoc;
    // using regex to ensure all iterations of settings change are removed (globally)
    viewer = viewer.replace(/font-style: italic; /g, "");
    viewer = viewer.replace(/font-weight: bold; /g, "");
    viewer = viewer.replace(/text-decoration: underline; /g, "");
    viewer = viewer.replace(oldScheme, "/color-schemes/colors.css");
    viewer = viewer.replace(oldSize + ";", "font-size: 290%;");

    document.getElementById("settings-view").srcdoc = viewer;
    console.log(viewer);

    oldScheme = "/color-schemes/colors.css";
    oldSize = "font-size: 290%";
    italic = false;
    bold = false;
    underline = false;

    document.getElementById(previousButton).style = "background-color: rgb(235, 235, 168)";
    document.getElementById("standard").style = "background-color: rgb(34, 168, 1)";
    previousButton = "standard";

    document.getElementById("undo-styles").setAttribute("class", "btn btn-secondary btn-block");
    document.getElementById("undo-styles").disabled = true;
  });

  /**
   * when apply-all-settings button is clicked it takes the current settings 
   * inside the iframe and applys them to the main html document makes undo
   * clickable.
   */
  $("#apply-all-settings").click(function () {
    console.log("clicked apply-all-settings");

    document.getElementById("color-scheme").href = oldScheme;

    var textToChange = document.getElementById("main-information").style;
    var currentSize = oldSize.replace("font-size: ", "");
    console.log(currentSize);
    textToChange.fontSize = currentSize;

    if (italic == true) {
      textToChange.fontStyle = "italic";
    } else {
      textToChange.fontStyle = "unset";
    }

    if (bold == true) {
      textToChange.fontWeight = "bold";
    } else {
      textToChange.fontWeight = "unset";
    }

    if (underline == true) {
      textToChange.textDecoration = "underline";
    } else {
      textToChange.textDecoration = "none";
    }
    console.log(document.getElementById("undo-styles").disabled);
    document.getElementById("undo-styles").setAttribute("class", "btn btn-outline-primary btn-block");
    document.getElementById("undo-styles").disabled = false;
  });
});
