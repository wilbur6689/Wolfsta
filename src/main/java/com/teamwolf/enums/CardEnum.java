package com.teamwolf.enums;

import org.apache.log4j.Logger;

public enum CardEnum {
    ACEOFSPADES1(1),
    ACEOFSPADES2(2),
    ACEOFCLUBS1(3),
    ACEOFCLUBS2(4),
    ACEOFDIAMONDS1(5),
    ACEOFDIAMONDS2(6),
    ACEOFHEARTS1(7),
    ACEOFHEARTS2(8),
    TWOOFSPADES1(9),
    TWOOFSPADES2(10),
    TWOOFCLUBS1(11),
    TWOOFCLUBS2(12),
    TWOOFDIAMONDS1(13),
    TWOOFDIAMONDS2(14),
    TWOOFHEARTS1(15),
    TWOOFHEARTS2(16),
    THREEOFSPADES1(17),
    THREEOFSPADES2(18),
    THREEOFCLUBS1(19),
    THREEOFCLUBS2(20),
    THREEOFDIAMONDS1(21),
    THREEOFDIAMONDS2(22),
    THREEOFHEARTS1(23),
    THREEOFHEARTS2(24),
    FOUROFSPADES1(25),
    FOUROFSPADES2(26),
    FOUROFCLUBS1(27),
    FOUROFCLUBS2(28),
    FOUROFDIAMONDS1(29),
    FOUROFDIAMONDS2(30),
    FOUROFHEARTS1(31),
    FOUROFHEARTS2(32),
    FIVEOFSPADES1(33),
    FIVEOFSPADES2(34),
    FIVEOFCLUBS1(35),
    FIVEOFCLUBS2(36),
    FIVEOFDIAMONDS1(37),
    FIVEOFDIAMONDS2(38),
    FIVEOFHEARTS1(39),
    FIVEOFHEARTS2(40),
    SIXOFSPADES1(41),
    SIXOFSPADES2(42),
    SIXOFCLUBS1(43),
    SIXOFCLUBS2(44),
    SIXOFDIAMONDS1(45),
    SIXOFDIAMONDS2(46),
    SIXOFHEARTS1(47),
    SIXOFHEARTS2(48),
    SEVENOFSPADES1(49),
    SEVENOFSPADES2(50),
    SEVENOFCLUBS1(51),
    SEVENOFCLUBS2(52),
    SEVENOFDIAMONDS1(53),
    SEVENOFDIAMONDS2(54),
    SEVENOFHEARTS1(55),
    SEVENOFHEARTS2(56),
    EIGHTOFSPADES1(57),
    EIGHTOFSPADES2(58),
    EIGHTOFCLUBS1(59),
    EIGHTOFCLUBS2(60),
    EIGHTOFDIAMONDS1(61),
    EIGHTOFDIAMONDS2(62),
    EIGHTOFHEARTS1(63),
    EIGHTOFHEARTS2(64),
    NINEOFSPADES1(65),
    NINEOFSPADES2(66),
    NINEOFCLUBS1(67),
    NINEOFCLUBS2(68),
    NINEOFDIAMONDS1(69),
    NINEOFDIAMONDS2(70),
    NINEOFHEARTS1(71),
    NINEOFHEARTS2(72),
    TENOFSPADES1(73),
    TENOFSPADES2(74),
    TENOFCLUBS1(75),
    TENOFCLUBS2(76),
    TENOFDIAMONDS1(77),
    TENOFDIAMONDS2(78),
    TENOFHEARTS1(79),
    TENOFHEARTS2(80),
    JACKOFSPADES1(81),
    JACKOFSPADES2(82),
    JACKOFCLUBS1(83),
    JACKOFCLUBS2(84),
    JACKOFDIAMONDS1(85),
    JACKOFDIAMONDS2(86),
    JACKOFHEARTS1(87),
    JACKOFHEARTS2(88),
    QUEENOFSPADES1(89),
    QUEENOFSPADES2(90),
    QUEENOFCLUBS1(91),
    QUEENOFCLUBS2(92),
    QUEENOFDIAMONDS1(93),
    QUEENOFDIAMONDS2(94),
    QUEENOFHEARTS1(95),
    QUEENOFHEARTS2(96),
    KINGOFSPADES1(97),
    KINGOFSPADES2(98),
    KINGOFCLUBS1(99),
    KINGOFCLUBS2(100),
    KINGOFDIAMONDS1(101),
    KINGOFDIAMONDS2(102),
    KINGOFHEARTS1(103),
    KINGOFHEARTS2(104),
    JOKER1(105),
    JOKER2(106),
    JOKER3(107),
    JOKER4(108);



    private final int cardId;
    protected static Logger log = Logger.getRootLogger();

    CardEnum(int x){
        this.cardId = x;
    }

    public int getId(){
        return cardId;
    }

    /**
     * 1(ace) - 13(king) 14for Jokers
     * @return rank
     */
    public int getRank(){
        log.trace("getting rank of a card");
        return ((this.cardId-1)/ 8 + 1);
    }

    /**
     * @return point value of card
     */
    public int getValue(){
        log.trace("getting value of a card");
        int rank = this.getRank();
        if (rank<=2){
            return  20;
        }
        else if(rank == 3){
            if(this.cardId <=20){
                return 5;
            }
            else{
                return 100;
            }
        }
        else if(rank<=7){
            return 5;
        }
        else if(rank<=13){
            return 10;
        }
        else if (rank == 14){
            return 50;
        }
        else{
            log.error("A card Has an invalid rank");
            return 0;
        }
    }

    /**
     *
     * @param id the id of the card in database terms
     * @return the card enum
     */
    public static CardEnum getCardbyCardId(int id){
        for(CardEnum c : CardEnum.values()){
            if(c.cardId == id) return c;
        }
        log.error("There is no card for that id");
        return null;
    }
}
