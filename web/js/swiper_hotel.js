const swiper5 = new Swiper(".Cdn > .swiper", {
	  loop: true,
	  slidesPerView: 2,
	  slidesPerGroup: 1,

	  pagination: {
	    el: ".swiper-pagination-bullets",
	    type:'bullets',
	    clickable: true, 
	  },
	  
	  autoplay: {
	    delay: 5000,
	  },
	  on: {
		    slideChange: function () {
		    	$('.swiper-pagination-bullets').removeClass('swiper-pagination-bullets-active');
		    }
	  	},
	  
	});


const swiper6 = new Swiper(".Best > .swiper", {
	  slidesPerView: 7,
	  slidesPerGroup: 1,
	  
	  navigation: {
		    nextEl: ".hotelsugsoList-button-next",
		    prevEl: ".hotelsugsoList-button-prev",
		  },
	});

const swiper7 = new Swiper(".special > .swiper", {
	slidesPerView: 7,
	slidesPerGroup: 1,
	
	navigation: {
		nextEl: ".hotelspecialList-button-next",
		prevEl: ".hotelspecialList-button-prev",
	},
});

















