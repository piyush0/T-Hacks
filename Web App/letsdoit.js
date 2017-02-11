window.onload=function toDataUrl(url, callback) {
  var xhr = new XMLHttpRequest();
  xhr.onload = function() {
    var reader = new FileReader();
    reader.onloadend = function() {
      callback(reader.result);
    }
    reader.readAsDataURL(xhr.response);
  };
  xhr.open('POST', url);
  xhr.responseType = 'blob';
  xhr.send();
}

toDataUrl('10.60.34.245:8000/get_score', function(base64Img) {
  console.log(base64Img);
});
