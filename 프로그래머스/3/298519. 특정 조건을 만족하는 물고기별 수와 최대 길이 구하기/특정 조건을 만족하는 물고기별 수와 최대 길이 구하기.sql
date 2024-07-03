select count(*) as FISH_COUNT, MAX(LENGTH) as MAX_LENGTH, FISH_TYPE
from fish_info
group by fish_type
having avg(ifnull(length, 10)) >= 33
order by fish_type;