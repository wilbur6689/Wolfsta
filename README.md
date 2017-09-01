# Wolfsta
A Repository for team wolf to build a game.

Rules for Canasta:

Canasta Rules

These are the Canasta rules as defined by "Hoyle's Rules of Games".

Overview

Your goal is to beat your opponent by scoring more points. You score points by melding cards, and making as many canastas as possible. A canasta is a meld of at least seven cards of the same rank.
Each player starts with 15 cards in hand. Yours are visible at the bottom of the window.
Both players take turns in drawing one card from the stock, and discarding one card on the discard pile (in that order). Both players take turns in drawing the first card.
After drawing a card, a player may meld cards if (s)he wants to. Cards are melded in columns of at least three cards; e.g. you can meld three Kings, or four Fives. You cannot meld sequences like Four-Five-Six. Once a card has been melded, it cannot be taken back into the hand (except with the Undo meld option).
When a player has melded his cards, he ends his turn by discarding a card. At that point, his melded cards are checked to see if they conform to the canasta rules. Discarding a card is not necessary if the player can go out by melding all of his cards.
Instead of drawing a card from the stock, a player may take the entire discard pile. However, this is only allowed if he can directly meld the top card.
A hand is over when one of the players has no cards left in his hand, or when there are no cards left on the stock. The scores of both players are then computed, and a new hand is dealt. A player can only finish a hand when he has at least one or two canastas, depending on the setting of the corresponding option.
A canasta match is over when one of the players reaches 5,000 points.

Rules

If a rank is melded, it must contain at least three cards on the table.
It is possible to add a wildcard to a column of cards of any rank on the table. The wildcard is then seen as a card of that rank (but the value remains unchanged). However, there may never be more wildcards than natural cards within one meld.
At the beginning of a hand, the top card of the stock is automatically turned around and placed on the discard pile. If this card is a Red Three or a wildcard, the procedure is repeated until the top card of the discard pile is neither a Red Three nor a wildcard.
When a new hand is dealt, the hands of both players are checked to see if they contain a Red Three. If there is one, that Three is then automatically melded, and an extra card is dealt to the hand out of which it came. This procedure is repeated until neither player has any Red Threes left in his hand.
Black Threes may not be melded, except when the player can go out by melding a column of three or four Black Threes. These Black Threes must then be the last cards to be melded.
The discard pile can be frozen by discarding a wildcard or a Red Three. A freeze is indicated by brackets [..] and means that both players are only allowed to take the discard pile if they can meld the top card using only the cards in their hand, as if they had no cards on the table. For example, if the discard pile is frozen and the top card is a Seven, the pile may only be taken if the player has two Sevens in his hand, even though he has a column of three Sevens on the table. Also, no wildcards may be used in melding: if he has one Seven and a wildcard in hand - instead of two Sevens- he may not take the discard pile.
If you have taken the discard pile but discover that you cannot use the top card, you can restore the pile by clicking on it. This carries a penalty of 50 points.
It is never allowed to take the discard pile when the top card is a Black Three, a Red Three or a wildcard. Thus, Black Threes can be used to freeze the pile for a single turn.
The first time a player melds cards in a hand, their value must be at least a certain minimum. When a player goes out in one turn ('goes out concealed'), this requirement does not apply.
A player can only go out when he has at least one or two canastas, depending on the 'Canastas needed to go out' setting. If a player has less than this number of canastas, and only has one card left that he should discard, he has to 'pass the discard', i.e., he does not discard that last card.
Taking a discard pile that only contains one card is never allowed if you only have one card in your hand.
When there are no more cards on the stock a special situation occurs:
1. If the discard pile is not frozen and you can add the top discard to one of your melds, you must take that top discard. Only that card is removed from the pile; you do not receive the rest. You must discard normally.
2. If you can take the discard pile in the normal way, you may. You must discard normally.
3. If you can do neither, press the 'End Hand' button that appears in the upper right-hand corner of the screen. You do not have to discard.


Scoring: Card Values and Bonuses

The cards have the following point values: 

Cards	                                      Value
Four, Five, Six, Seven, Black Three	        5
Eight, Nine, Ten, Jack, Queen, King	        10
Deuce, Ace	                                20
Joker	                                      50
Red Three	                                  100



The following bonuses are awarded:

A mixed canasta is worth 300 points, a natural canasta 500 points, and a wildcard canasta 1,000 points.
The player that goes out gets a bonus of 100 points. If this player had no cards (except Red Threes) on the table directly before going out ('goes out concealed'), this bonus is doubled to 200 points.
If a player has all four Red Threes on the table, their score of 400 points is doubled to 800 points.
If a player goes out and his opponent has melded nothing but Red Threes, the value of those Red Threes is deducted from his opponent's score.



Total Score

At the end of a hand, the total score is calculated as follows:
The sum of the values of the cards on the table forms the initial score value.
Bonuses for canastas, going out and Red Threes are added to the score.
The values of the cards in hand are deducted from the score.
However, if you have selected Yes in the 'Canasta required for positive score' option, this calculation is only valid if you have at least the number of canastas as it is shown in the 'Canastas needed to go out' setting! If not, a negative score results consisting of the sum of the values of the cards in your hand and on the table. It is therefore always wise to get a canasta quickly, so that you don't run the risk of holding a large collection of cards if your opponent goes out. If you have selected No, the above does not apply.



Minimum

The first time in a hand that a player melds cards the sum of their values must be at least a certain minimum. This minimum depends on your current score in the following way:
Score	Minimum
Less than 0	15
From 0 to 1495	50
From 1500 to 2995	90
3000 or more	120



Notes:

The values of any Red Threes never contribute to the required minimum. Canastas contribute only if the corresponding option is enabled.
If you turn off the option Top card counts for initial meld, you must add the value of the top card to the minimum needed when taking the discard pile. E.g., if the minimum is 50 and you took an Ace from the pile, you must have 50 + 20 = 70 points on the table to get the rest of the pile. The value of the Ace is thus not counted toward the 50 point minimum.
If you took the top card of the discard pile but you discover that you cannot use it, you can put it back by clicking on the pile again. This carries a penalty of 50 points.
