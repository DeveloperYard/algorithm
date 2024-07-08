select ID, case
    when rank_size <= ((select count(*) from ecoli_data))*0.25 then 'CRITICAL'
    when rank_size <= ((select count(*) from ecoli_data))*0.5 then 'HIGH'
    when rank_size <= ((select count(*) from ecoli_data))*0.75 then 'MEDIUM'
    else 'LOW'
    end as COLONY_NAME
from (
    select ID, (rank() over (order by size_of_colony desc)) as rank_size
    from ecoli_data
) as AA
order by ID asc;