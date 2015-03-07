<?php

$data = '
[
  {
    "term": "GALLOWS",
    "part": "n.",
    "definition": "A stage for the performance of miracle plays, in which the leading actor is translated to heaven. In this country the gallows is chiefly remarkable for the number of persons who escape it.",
    "quote": [
      "Whether on the gallows high",
      "Or where blood flows the reddest,",
      "The noblest place for man to die —",
      "Is where he died the deadest."
    ],
    "author": "Old play"
  },
  {
    "term": "GENEALOGY",
    "part": "n.",
    "definition": "An account of one\'s descent from an ancestor who did not particularly care to trace his own."
  },
  {
    "term": "GENEROUS",
    "part": "adj.",
    "definition": "Originally this word meant noble by birth and was rightly applied to a great multitude of persons. It now means noble by nature and is taking a bit of a rest."
  },
  {
    "term": "GENTEEL",
    "part": "adj.",
    "definition": "Refined, after the fashion of a gent.",
    "quote": [
      "Observe with care, my son, the distinction I reveal:",
      "A gentleman is gentle and a gent genteel.",
      "Heed not the definitions your \'Unabridged\' presents,",
      "For dictionary makers are generally gents."
    ],
    "author": "G.J."
  },
  {
    "term": "GLUTTON",
    "part": "n.",
    "definition": "A person who escapes the evils of moderation by committing dyspepsia."
  },
  {
    "term": "GOOD",
    "part": "adj.",
    "definition": "Sensible, madam, to the worth of this present writer. Alive, sir, to the advantages of letting him alone."
  },
  {
    "term": "GOUT",
    "part": "n.",
    "definition": "A physician\'s name for the rheumatism of a rich patient."
  },
  {
    "term": "GRACES",
    "part": "n.",
    "definition": "Three beautiful goddesses, Aglaia, Thalia and Euphrosyne, who attended upon Venus, serving without salary. They were at no expense for board and clothing, for they ate nothing to speak of and dressed according to the weather, wearing whatever breeze happened to be blowing."
  }
]
';

print($_GET['callback'] .'('. $data .')');
