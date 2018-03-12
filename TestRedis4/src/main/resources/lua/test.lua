--
-- Created by IntelliJ IDEA.
-- User: james
-- Date: 2018/2/24
-- Time: 16:03
-- To change this template use File | Settings | File Templates.
--

redis.call('SET', KEYS[1], ARGV[1])
redis.call('SET', KEYS[2], ARGV[2])

local currentVal = redis.call('GET', KEYS[1])

if currentVal == ARGV[1]
then redis.call('SET', KEYS[1], ARGV[1])
return 11
end
return 22