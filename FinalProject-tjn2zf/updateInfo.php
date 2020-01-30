<?php
// Based on lecture notes on the einstein ajax example


	$infoId = empty($_GET['infoId']) ? 'quote0' : $_GET['infoId'];

	switch($infoId) {
		case 'quote1':
			$info = 'Your wipe sweat from your brow as you travel through the murky swamp. Suddenly, a scaled creature arises from the muck! You draw your bow and shoot. You land the shot! You deal 8 damage and your arrow pierces the creatures hard carrapace.</i>';
			break;
		case 'quote2':
			$info = 'The creature, which you recognize now as a Chuul, rushes you and swings its might claws! It catches you with a heavy blow to the shoulder, and you take 15 damage, <strong>OUCH!</strong>';
			break;
		case 'quote3':
			$info = 'You switch to your longsword and attempt to plunge it into the beast. But you fail! Your sword deflects off the Chuul! You run back and prepare your sword once again';
			break;
		case 'quote4':
			$info = 'The Chuul comes at you with bloodlust and swings  once more. It fails! You jump over its claws and hold your sword high over your head. Your move...';
			break;
		default: 
			$info = "<strong>CRITICAL HIT!!</strong> You cleave the creature right in two! You collect yourself and carry on your way. Best not to linger here, the gods only know what else lurks around the next corner..."; 
			break;
	}

	print $info;
?>