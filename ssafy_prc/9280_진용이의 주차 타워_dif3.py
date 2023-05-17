from collections import deque
import heapq


def sol(parkingSpaceUnitCost, carWeight, process):
    ans = 0

    q = []

    waitingQ = deque()
    spaceRecord = dict()

    for idx, cost in enumerate(parkingSpaceUnitCost):
        heapq.heappush(q, (idx, cost))

    for i in range(len(process)):
        # print(spaceRecord)
        carNum = process[i]

        if carNum > 0:
            carNum -= 1
            # print("carNum : "+str(carNum))
            if not q: # 주차장 자리 x
                waitingQ.append(carNum)
            else: # 주차장 자리 o
                if not waitingQ: # 대기하는 차량 없음
                    # print("carNum in else condition : " + str(carNum))
                    space_idx, space_cost = heapq.heappop(q)
                    spaceRecord[carNum] = space_idx
                    ans += carWeight[carNum] * space_cost
                else: # 대기하는 차량 있음
                    waitingQ.append(carNum)
                    curCar = waitingQ.popleft()
                    space_idx, space_cost = heapq.heappop(q)
                    ans += carWeight[curCar] * space_cost
                    spaceRecord[curCar] = space_idx

        else:  # 출차
            carNum *= (-1)
            carNum -= 1
            heapq.heappush(q, (spaceRecord[carNum], parkingSpaceUnitCost[spaceRecord[carNum]]))
            del spaceRecord[carNum]
            if waitingQ:
                curCar = waitingQ.popleft()
                space_idx, space_cost = heapq.heappop(q)
                spaceRecord[curCar] = space_idx
                ans += carWeight[curCar] * space_cost

    return ans


for tc in range(int(input())):
    n, m = map(int, input().split())
    parkingSpaceUnitCost = []
    carWeight = []
    process = []
    for i in range(n):
        parkingSpaceUnitCost.append(int(input()))

    for j in range(m):
        carWeight.append(int(input()))

    for k in range(m*2):
        process.append(int(input()))

    res = sol(parkingSpaceUnitCost, carWeight, process)
    print(f'#{tc+1} {res}')