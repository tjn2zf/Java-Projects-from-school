//
//	Taken from in class code for the animal photo viewer
//
//
var title = 'Player Races';

var photos = [
	{
		uri : 'images/human.png',
		title : 'Human'
	},
	{
		uri : 'images/elf.jpg',
		title : 'Elf'
	},
	{
		uri : 'images/dwarf.png',
		title : 'Dwarf'
	},
	{
		uri : 'images/half-elf.png',
		title : 'Half-elf'
	},
	{
		uri : 'images/gnome.jpg',
		title : 'Gnome'
	},
	{
		uri : 'images/halfling.png',
		title : 'Halfling'
	},
	{
		uri : 'images/tiefling.png',
		title : 'Tiefling'
	},
	{
		uri : 'images/dragonborn.png',
		title : 'Dragonborn'
	},
	{
		uri : 'images/orc.jpg',
		title : 'Half-Orc'
	},
];

function prepareDisplay(photos) {
    $("#title").html(title);
    
    if(photos.length < 1) return;
    
    var firstPhoto = photos[0];
    $("#imageHolder").attr("src", firstPhoto.uri);
    $("#photoTitle").html(firstPhoto.title);
}

function displayItem(event) {
    console.dir(event);
    
    var data = event.data;
    var item = data.item;
    
    $("#imageHolder").attr("src", item.uri);
    $("#photoTitle").html(item.title);
}

function populateMenu(items){
    for(var i=0; i < items.length; i++){
        var item = items[i];
        
        var menuItem = $("<li>" + item.title + "</li>");
        
        $("#menuItems").append(menuItem);
        
        var eventData = {
            'num' : i,
            'item' : item
        };
        
        menuItem.click(eventData, displayItem);
    }
}

$(document).ready(
    function(){
        prepareDisplay(photos);
        populateMenu(photos);
    }
);
