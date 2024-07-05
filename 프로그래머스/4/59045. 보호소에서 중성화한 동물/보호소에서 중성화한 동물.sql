select ai.ANIMAL_ID, ai.ANIMAL_TYPE, ai.NAME
from animal_ins ai left join animal_outs ao on ai.animal_id = ao.animal_id
where ai.animal_id is not null and ai.sex_upon_intake like "Intact%" and ao.sex_upon_outcome regexp '^(Spayed|Neutered)' 
order by ai.animal_id asc;