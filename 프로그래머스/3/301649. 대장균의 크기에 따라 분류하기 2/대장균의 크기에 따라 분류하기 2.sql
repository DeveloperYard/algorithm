select ID, case
    when rank_size <= ((select count(*) from ECOLI_DATA) * 0.25) then 'CRITICAL'
    when rank_size <= ((select count(*) from ECOLI_DATA) * 0.5) then 'HIGH'
    when rank_size <= ((select count(*) from ECOLI_DATA) * 0.75) then 'MEDIUM'
    else 'LOW'
end as COLONY_NAME
from (
    select ID, (rank() over (order by SIZE_OF_COLONY desc)) as rank_size
    from ECOLI_DATA
) as AA
order by 1;