let arr = Array.prototype.slice.call(document.getElementsByTagName("img"));

youtubeLinks = arr.filter(img => img.src.includes('youtube')); // filter for youtube links

youtubeLinks.forEach(item => {
    let wrapper = document.createElement('div');
    wrapper.classList.add('button-div');

    let playBtn = document.createElement('img');
    playBtn.src = "../img/play_button.png";
    wrapper.appendChild(playBtn);
    item.parentNode.appendChild(wrapper);


    item.parentNode.target = "_blank"; // open in new tab
    item.parentNode.classList.add("video");

});