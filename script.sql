-- Insert 60 Cat Breeds
INSERT INTO cat_breeds (name, origin, size, coat, color, life_span, temperament, description, created_date, modified_date) VALUES
('Siamese', 'Thailand', 4, 'Short', 'Seal Point', '12-15 years', 'Active', 'The Siamese cat is known for its slender body and vocal personality.', CURRENT_DATE, CURRENT_DATE),
('Persian', 'Iran', 5, 'Long', 'White', '10-15 years', 'Calm', 'The Persian is a quiet, sweet cat that loves lounging around.', CURRENT_DATE, CURRENT_DATE),
('Maine Coon', 'USA', 9, 'Long', 'Tabby', '10-13 years', 'Friendly', 'Maine Coon cats are large, friendly, and good-natured.', CURRENT_DATE, CURRENT_DATE),
('Sphynx', 'Canada', 3, 'Hairless', 'Pink', '9-15 years', 'Energetic', 'The Sphynx is a hairless breed known for its playful behavior.', CURRENT_DATE, CURRENT_DATE),
('Bengal', 'USA', 7, 'Short', 'Spotted', '10-16 years', 'Active', 'Bengal cats are known for their leopard-like spots and energetic personality.', CURRENT_DATE, CURRENT_DATE),
('British Shorthair', 'United Kingdom', 6, 'Short', 'Blue', '12-17 years', 'Loyal', 'British Shorthairs are known for their calm and easygoing demeanor.', CURRENT_DATE, CURRENT_DATE),
('Abyssinian', 'Ethiopia', 5, 'Short', 'Ruddy', '9-13 years', 'Playful', 'Abyssinians are energetic cats that love to climb and explore.', CURRENT_DATE, CURRENT_DATE),
('Ragdoll', 'USA', 8, 'Long', 'Blue', '15-20 years', 'Affectionate', 'Ragdolls are large cats known for their gentle and relaxed nature.', CURRENT_DATE, CURRENT_DATE),
('Scottish Fold', 'Scotland', 5, 'Short', 'Gray', '11-14 years', 'Curious', 'Scottish Folds are known for their distinctive folded ears and loving nature.', CURRENT_DATE, CURRENT_DATE),
('Russian Blue', 'Russia', 5, 'Short', 'Blue', '15-20 years', 'Reserved', 'Russian Blue cats are known for their shy and sensitive personality.', CURRENT_DATE, CURRENT_DATE),
('Birman', 'France', 6, 'Long', 'Seal Point', '12-16 years', 'Gentle', 'The Birman is a friendly and affectionate breed, often called the sacred cat of Burma.', CURRENT_DATE, CURRENT_DATE),
('Oriental', 'Thailand', 4, 'Short', 'Varies', '12-15 years', 'Vocal', 'Oriental cats are known for their striking appearance and talkative nature.', CURRENT_DATE, CURRENT_DATE),
('Devon Rex', 'United Kingdom', 3, 'Short', 'Curly', '9-13 years', 'Mischievous', 'Devon Rex cats are playful and love to interact with people.', CURRENT_DATE, CURRENT_DATE),
('American Shorthair', 'USA', 6, 'Short', 'Tabby', '15-20 years', 'Adaptable', 'American Shorthairs are easygoing cats with a calm temperament.', CURRENT_DATE, CURRENT_DATE),
('Norwegian Forest', 'Norway', 9, 'Long', 'Brown', '14-16 years', 'Independent', 'Norwegian Forest cats are strong, large cats that love the outdoors.', CURRENT_DATE, CURRENT_DATE),
('Turkish Angora', 'Turkey', 4, 'Long', 'White', '12-18 years', 'Elegant', 'The Turkish Angora is known for its graceful body and soft coat.', CURRENT_DATE, CURRENT_DATE),
('Egyptian Mau', 'Egypt', 5, 'Short', 'Spotted', '12-15 years', 'Agile', 'Egyptian Maus are the fastest domestic cats, known for their athleticism.', CURRENT_DATE, CURRENT_DATE),
('Tonkinese', 'Canada', 4, 'Short', 'Mink', '12-16 years', 'Lively', 'Tonkinese cats are active and social, loving human interaction.', CURRENT_DATE, CURRENT_DATE),
('Chartreux', 'France', 5, 'Short', 'Gray', '12-15 years', 'Quiet', 'Chartreux cats are calm and known for their woolly blue-gray fur.', CURRENT_DATE, CURRENT_DATE),
('Ocicat', 'USA', 6, 'Short', 'Spotted', '12-15 years', 'Friendly', 'Ocicats are known for their wild appearance but domestic temperament.', CURRENT_DATE, CURRENT_DATE),
('Himalayan', 'USA', 6, 'Long', 'Seal Point', '9-15 years', 'Laid-back', 'The Himalayan is a cross between a Persian and a Siamese, known for its calm demeanor.', CURRENT_DATE, CURRENT_DATE),
('Japanese Bobtail', 'Japan', 4, 'Short', 'White and Black', '12-16 years', 'Active', 'The Japanese Bobtail is known for its short "bobbed" tail and playful nature.', CURRENT_DATE, CURRENT_DATE),
('Burmese', 'Myanmar', 5, 'Short', 'Sable', '10-17 years', 'Affectionate', 'Burmese cats are people-oriented and love to follow their owners.', CURRENT_DATE, CURRENT_DATE),
('Manx', 'Isle of Man', 5, 'Short', 'Varies', '8-14 years', 'Loyal', 'Manx cats are known for their taillessness and strong hindquarters.', CURRENT_DATE, CURRENT_DATE),
('Selkirk Rex', 'USA', 6, 'Curly', 'Varies', '10-15 years', 'Calm', 'Selkirk Rex cats are known for their curly coats and calm nature.', CURRENT_DATE, CURRENT_DATE),
('Cornish Rex', 'United Kingdom', 3, 'Curly', 'Varies', '11-15 years', 'Energetic', 'The Cornish Rex is known for its playful and kitten-like behavior.', CURRENT_DATE, CURRENT_DATE),
('Exotic Shorthair', 'USA', 5, 'Short', 'Varies', '8-15 years', 'Laid-back', 'Exotic Shorthairs are known for their plush coats and calm temperament.', CURRENT_DATE, CURRENT_DATE),
('Balinese', 'USA', 5, 'Long', 'Seal Point', '15-20 years', 'Intelligent', 'The Balinese is a long-haired version of the Siamese, known for its intelligence.', CURRENT_DATE, CURRENT_DATE),
('LaPerm', 'USA', 4, 'Curly', 'Varies', '10-15 years', 'Affectionate', 'LaPerms are known for their unique curly coats and friendly nature.', CURRENT_DATE, CURRENT_DATE),
('Turkish Van', 'Turkey', 8, 'Semi-long', 'White and Auburn', '12-17 years', 'Energetic', 'The Turkish Van is known for its love of water and playful behavior.', CURRENT_DATE, CURRENT_DATE),
('Savannah', 'USA', 10, 'Short', 'Spotted', '12-20 years', 'Active', 'Savannah cats are large, exotic-looking cats that are energetic and social.', CURRENT_DATE, CURRENT_DATE),
('Snowshoe', 'USA', 6, 'Short', 'Seal Point', '14-17 years', 'Gentle', 'Snowshoe cats are known for their striking markings and gentle demeanor.', CURRENT_DATE, CURRENT_DATE),
('Ragamuffin', 'USA', 9, 'Long', 'Varies', '12-16 years', 'Sweet', 'Ragamuffins are large, lovable cats known for their docile temperament.', CURRENT_DATE, CURRENT_DATE),
('Singapura', 'Singapore', 3, 'Short', 'Ticked', '11-15 years', 'Active', 'Singapura cats are tiny but full of energy and curiosity.', CURRENT_DATE, CURRENT_DATE),
('Siberian', 'Russia', 9, 'Long', 'Varies', '10-18 years', 'Loyal', 'Siberian cats are strong and loyal, often compared to dogs in their devotion.', CURRENT_DATE, CURRENT_DATE),
('American Curl', 'USA', 4, 'Short', 'Varies', '12-16 years', 'Curious', 'American Curls are known for their unique curled-back ears.', CURRENT_DATE, CURRENT_DATE),
('Pixie-bob', 'USA', 7, 'Short', 'Spotted', '13-16 years', 'Social', 'Pixie-bobs are large, dog-like cats known for their friendly nature.', CURRENT_DATE, CURRENT_DATE),
('Lykoi', 'USA', 4, 'Sparse', 'Gray', '10-15 years', 'Curious', 'Lykoi cats are also called "werewolf cats" due to their unique appearance.', CURRENT_DATE, CURRENT_DATE),
('Chausie', 'USA', 9, 'Short', 'Tabby', '12-14 years', 'Active', 'Chausies are large, energetic cats that love to play.', CURRENT_DATE, CURRENT_DATE),
('Burmilla', 'United Kingdom', 5, 'Short', 'Silver', '10-15 years', 'Playful', 'The Burmilla is a mix between the Burmese and Chinchilla Persian.', CURRENT_DATE, CURRENT_DATE),
('Javanese', 'USA', 5, 'Long', 'Varies', '10-15 years', 'Intelligent', 'Javanese cats are smart and love to interact with their owners.', CURRENT_DATE, CURRENT_DATE),
('Korat', 'Thailand', 4, 'Short', 'Blue', '15-20 years', 'Affectionate', 'Korats are a rare breed known for their affectionate and loyal nature.', CURRENT_DATE, CURRENT_DATE),
('Sokoke', 'Kenya', 6, 'Short', 'Tabby', '12-15 years', 'Active', 'The Sokoke is a rare, wild-looking breed known for its independence.', CURRENT_DATE, CURRENT_DATE),
('Cymric', 'Isle of Man', 6, 'Long', 'Varies', '9-14 years', 'Loyal', 'The Cymric is a long-haired version of the Manx cat.', CURRENT_DATE, CURRENT_DATE),
('Toyger', 'USA', 7, 'Short', 'Striped', '10-15 years', 'Playful', 'Toygers are bred to look like miniature tigers and are very active.', CURRENT_DATE, CURRENT_DATE),
('Peterbald', 'Russia', 3, 'Hairless', 'Varies', '12-15 years', 'Curious', 'Peterbald cats are hairless and love to explore their surroundings.', CURRENT_DATE, CURRENT_DATE),
('Munchkin', 'USA', 4, 'Short', 'Varies', '12-15 years', 'Social', 'Munchkin cats are known for their short legs and playful nature.', CURRENT_DATE, CURRENT_DATE),
('Khao Manee', 'Thailand', 4, 'Short', 'White', '10-12 years', 'Playful', 'Khao Manee cats are a rare, white breed known for their bright eyes.', CURRENT_DATE, CURRENT_DATE),
('Bambino', 'USA', 3, 'Hairless', 'Pink', '9-12 years', 'Affectionate', 'Bambino cats are a mix of the Munchkin and Sphynx breeds, known for their small size.', CURRENT_DATE, CURRENT_DATE),
('Donskoy', 'Russia', 4, 'Hairless', 'Gray', '12-15 years', 'Curious', 'Donskoy cats are hairless and known for their friendly, dog-like personality.', CURRENT_DATE, CURRENT_DATE),
('Serengeti', 'USA', 8, 'Short', 'Spotted', '8-12 years', 'Active', 'Serengeti cats are a wild-looking breed known for their energy and grace.', CURRENT_DATE, CURRENT_DATE),
('Australian Mist', 'Australia', 5, 'Short', 'Spotted', '12-16 years', 'Friendly', 'Australian Mist cats are a people-friendly breed known for their calm nature.', CURRENT_DATE, CURRENT_DATE),
('Cheetoh', 'USA', 9, 'Short', 'Spotted', '12-16 years', 'Active', 'Cheetoh cats are a hybrid breed, known for their wild appearance and active nature.', CURRENT_DATE, CURRENT_DATE),
('American Bobtail', 'USA', 8, 'Short', 'Varies', '13-16 years', 'Confident', 'American Bobtails are known for their short tails and dog-like personalities.', CURRENT_DATE, CURRENT_DATE);

-- Insert 60 Dog Breeds
INSERT INTO dog_breeds (name, origin, size, coat, color, life_span, temperament, description, created_date, modified_date) VALUES
('Labrador Retriever', 'Canada', 8, 'Short', 'Yellow', '10-12 years', 'Friendly', 'Labradors are known for their friendly, outgoing personalities and are great family dogs.', CURRENT_DATE, CURRENT_DATE),
('German Shepherd', 'Germany', 9, 'Medium', 'Black and Tan', '9-13 years', 'Intelligent', 'German Shepherds are strong, loyal dogs that excel in many working roles.', CURRENT_DATE, CURRENT_DATE),
('Golden Retriever', 'Scotland', 8, 'Medium', 'Golden', '10-12 years', 'Affectionate', 'Golden Retrievers are known for their loving and gentle personalities.', CURRENT_DATE, CURRENT_DATE),
('Bulldog', 'United Kingdom', 6, 'Short', 'White and Brindle', '8-10 years', 'Courageous', 'Bulldogs are calm and courageous dogs with a distinctive wrinkled face.', CURRENT_DATE, CURRENT_DATE),
('Beagle', 'United Kingdom', 5, 'Short', 'Tri-color', '12-15 years', 'Curious', 'Beagles are small hounds known for their exceptional sense of smell and friendly nature.', CURRENT_DATE, CURRENT_DATE),
('Poodle', 'Germany', 6, 'Curly', 'Black', '12-15 years', 'Intelligent', 'Poodles are highly intelligent dogs, known for their hypoallergenic curly coats.', CURRENT_DATE, CURRENT_DATE),
('Rottweiler', 'Germany', 9, 'Short', 'Black and Tan', '8-10 years', 'Confident', 'Rottweilers are powerful dogs, often used for guarding and protection.', CURRENT_DATE, CURRENT_DATE),
('Yorkshire Terrier', 'United Kingdom', 3, 'Long', 'Blue and Tan', '13-16 years', 'Spunky', 'Yorkshire Terriers are small, feisty dogs with a big personality.', CURRENT_DATE, CURRENT_DATE),
('Boxer', 'Germany', 8, 'Short', 'Fawn', '10-12 years', 'Energetic', 'Boxers are playful, energetic dogs that love to interact with their families.', CURRENT_DATE, CURRENT_DATE),
('Dachshund', 'Germany', 4, 'Short', 'Brown', '12-16 years', 'Clever', 'Dachshunds are small, clever dogs known for their long bodies and lively nature.', CURRENT_DATE, CURRENT_DATE),
('Siberian Husky', 'Russia', 9, 'Medium', 'Gray and White', '12-14 years', 'Energetic', 'Siberian Huskies are athletic, energetic dogs known for their striking appearance.', CURRENT_DATE, CURRENT_DATE),
('Chihuahua', 'Mexico', 2, 'Short', 'Fawn', '14-16 years', 'Bold', 'Chihuahuas are small, bold dogs known for their sassy and confident nature.', CURRENT_DATE, CURRENT_DATE),
('Shih Tzu', 'Tibet', 3, 'Long', 'White and Gold', '10-18 years', 'Loyal', 'Shih Tzus are known for their loyal nature and luxurious coats.', CURRENT_DATE, CURRENT_DATE),
('Great Dane', 'Germany', 10, 'Short', 'Black', '7-10 years', 'Gentle', 'Great Danes are giant dogs, often called gentle giants due to their calm demeanor.', CURRENT_DATE, CURRENT_DATE),
('Doberman Pinscher', 'Germany', 9, 'Short', 'Black and Tan', '10-12 years', 'Fearless', 'Dobermans are known for their alertness, intelligence, and protective nature.', CURRENT_DATE, CURRENT_DATE),
('Australian Shepherd', 'USA', 8, 'Medium', 'Merle', '12-15 years', 'Energetic', 'Australian Shepherds are active, intelligent dogs known for their herding abilities.', CURRENT_DATE, CURRENT_DATE),
('Pembroke Welsh Corgi', 'Wales', 5, 'Short', 'Red and White', '12-15 years', 'Affectionate', 'Corgis are small herding dogs with a big personality and a lot of affection to give.', CURRENT_DATE, CURRENT_DATE),
('Cocker Spaniel', 'United Kingdom', 6, 'Medium', 'Golden', '12-15 years', 'Gentle', 'Cocker Spaniels are sweet, gentle dogs with beautiful, flowing coats.', CURRENT_DATE, CURRENT_DATE),
('Pug', 'China', 4, 'Short', 'Fawn', '13-15 years', 'Charming', 'Pugs are known for their wrinkled faces, curly tails, and charming personalities.', CURRENT_DATE, CURRENT_DATE),
('Mastiff', 'United Kingdom', 10, 'Short', 'Fawn', '6-10 years', 'Loyal', 'Mastiffs are large, powerful dogs known for their loyalty and protective instincts.', CURRENT_DATE, CURRENT_DATE),
('Basset Hound', 'France', 7, 'Short', 'Tri-color', '10-12 years', 'Laid-back', 'Basset Hounds are calm, easygoing dogs known for their long ears and excellent noses.', CURRENT_DATE, CURRENT_DATE),
('Border Collie', 'United Kingdom', 7, 'Medium', 'Black and White', '12-15 years', 'Intelligent', 'Border Collies are highly intelligent and energetic, often excelling in herding.', CURRENT_DATE, CURRENT_DATE),
('Saint Bernard', 'Switzerland', 10, 'Medium', 'White and Brown', '8-10 years', 'Gentle', 'Saint Bernards are giant, gentle dogs known for their rescue work in the Alps.', CURRENT_DATE, CURRENT_DATE),
('Bull Terrier', 'United Kingdom', 7, 'Short', 'White', '10-14 years', 'Playful', 'Bull Terriers are playful and energetic, known for their unique egg-shaped heads.', CURRENT_DATE, CURRENT_DATE),
('Akita', 'Japan', 9, 'Medium', 'White', '10-13 years', 'Loyal', 'Akitas are powerful dogs known for their loyalty and reserved nature.', CURRENT_DATE, CURRENT_DATE),
('Pomeranian', 'Germany', 2, 'Long', 'Orange', '12-16 years', 'Vivacious', 'Pomeranians are small, fluffy dogs with big personalities and lots of energy.', CURRENT_DATE, CURRENT_DATE),
('Boston Terrier', 'USA', 5, 'Short', 'Black and White', '13-15 years', 'Friendly', 'Boston Terriers are friendly, compact dogs known for their tuxedo-like appearance.', CURRENT_DATE, CURRENT_DATE),
('French Bulldog', 'France', 6, 'Short', 'Brindle', '10-12 years', 'Charming', 'French Bulldogs are small, sturdy dogs known for their playful and affectionate nature.', CURRENT_DATE, CURRENT_DATE),
('Dalmatian', 'Croatia', 8, 'Short', 'White with Black Spots', '10-13 years', 'Energetic', 'Dalmatians are known for their spotted coats and high energy levels.', CURRENT_DATE, CURRENT_DATE),
('Newfoundland', 'Canada', 10, 'Medium', 'Black', '9-10 years', 'Gentle', 'Newfoundlands are large, gentle dogs known for their strength and water rescue abilities.', CURRENT_DATE, CURRENT_DATE),
('Weimaraner', 'Germany', 8, 'Short', 'Gray', '11-14 years', 'Energetic', 'Weimaraners are energetic dogs known for their sleek gray coats.', CURRENT_DATE, CURRENT_DATE),
('Shetland Sheepdog', 'United Kingdom', 7, 'Long', 'Sable', '12-14 years', 'Intelligent', 'Shetland Sheepdogs are intelligent, hardworking dogs known for their herding skills.', CURRENT_DATE, CURRENT_DATE),
('Alaskan Malamute', 'USA', 9, 'Thick', 'Gray and White', '10-14 years', 'Strong', 'Alaskan Malamutes are powerful dogs, originally bred for pulling heavy sleds.', CURRENT_DATE, CURRENT_DATE),
('Cavalier King Charles Spaniel', 'United Kingdom', 5, 'Medium', 'Blenheim', '12-14 years', 'Affectionate', 'Cavaliers are small, affectionate dogs known for their friendly personalities.', CURRENT_DATE, CURRENT_DATE),
('Samoyed', 'Russia', 9, 'Thick', 'White', '12-14 years', 'Friendly', 'Samoyeds are known for their fluffy white coats and friendly smiles.', CURRENT_DATE, CURRENT_DATE),
('Whippet', 'United Kingdom', 6, 'Short', 'Fawn', '12-15 years', 'Gentle', 'Whippets are sleek, fast dogs with a gentle nature.', CURRENT_DATE, CURRENT_DATE),
('Vizsla', 'Hungary', 7, 'Short', 'Red', '12-15 years', 'Energetic', 'Vizslas are energetic dogs known for their athleticism and loyalty.', CURRENT_DATE, CURRENT_DATE),
('Shiba Inu', 'Japan', 4, 'Short', 'Red', '13-16 years', 'Alert', 'Shiba Inus are independent dogs known for their fox-like appearance.', CURRENT_DATE, CURRENT_DATE),
('Staffordshire Bull Terrier', 'United Kingdom', 7, 'Short', 'Brindle', '12-14 years', 'Bold', 'Staffordshire Bull Terriers are courageous dogs known for their strength and affection.', CURRENT_DATE, CURRENT_DATE),
('Brittany', 'France', 7, 'Medium', 'Orange and White', '12-14 years', 'Energetic', 'Brittanys are energetic hunting dogs known for their friendly and playful nature.', CURRENT_DATE, CURRENT_DATE),
('Greyhound', 'United Kingdom', 9, 'Short', 'Black', '10-14 years', 'Laid-back', 'Greyhounds are known for their speed and gentle, calm temperament.', CURRENT_DATE, CURRENT_DATE),
('Bernese Mountain Dog', 'Switzerland', 9, 'Long', 'Tricolor', '7-10 years', 'Loyal', 'Bernese Mountain Dogs are large, loyal dogs known for their strength and affectionate nature.', CURRENT_DATE, CURRENT_DATE),
('Havanese', 'Cuba', 3, 'Long', 'White', '13-15 years', 'Playful', 'Havanese are small, playful dogs known for their soft, long coats.', CURRENT_DATE, CURRENT_DATE),
('Chow Chow', 'China', 8, 'Thick', 'Red', '8-12 years', 'Aloof', 'Chow Chows are known for their lion-like mane and independent nature.', CURRENT_DATE, CURRENT_DATE),
('English Setter', 'United Kingdom', 7, 'Medium', 'White and Lemon', '10-12 years', 'Gentle', 'English Setters are gentle, friendly dogs known for their bird hunting skills.', CURRENT_DATE, CURRENT_DATE),
('Irish Wolfhound', 'Ireland', 10, 'Medium', 'Gray', '6-8 years', 'Gentle', 'Irish Wolfhounds are the tallest of all dog breeds, known for their calm and friendly nature.', CURRENT_DATE, CURRENT_DATE),
('Pekingese', 'China', 3, 'Long', 'Gold', '12-14 years', 'Bold', 'Pekingese are small, bold dogs known for their regal appearance.', CURRENT_DATE, CURRENT_DATE),
('Airedale Terrier', 'United Kingdom', 8, 'Medium', 'Tan and Black', '10-12 years', 'Confident', 'Airedales are the largest of the terriers, known for their confidence and intelligence.', CURRENT_DATE, CURRENT_DATE),
('Saluki', 'Middle East', 7, 'Smooth', 'Fawn', '12-14 years', 'Graceful', 'Salukis are graceful, fast dogs known for their endurance in running.', CURRENT_DATE, CURRENT_DATE),
('Basenji', 'Congo', 6, 'Short', 'Red and White', '12-14 years', 'Independent', 'Basenjis are known for their independence and unique barkless nature.', CURRENT_DATE, CURRENT_DATE),
('Lhasa Apso', 'Tibet', 3, 'Long', 'White', '12-15 years', 'Independent', 'Lhasa Apsos are small, independent dogs known for their long, flowing coats.', CURRENT_DATE, CURRENT_DATE),
('Scottish Terrier', 'United Kingdom', 4, 'Wiry', 'Black', '11-13 years', 'Feisty', 'Scottish Terriers are small, confident dogs known for their independent nature.', CURRENT_DATE, CURRENT_DATE),
('Cane Corso', 'Italy', 9, 'Short', 'Black', '10-12 years', 'Loyal', 'Cane Corsos are powerful dogs known for their loyalty and protective instincts.', CURRENT_DATE, CURRENT_DATE),
('Papillon', 'France', 3, 'Long', 'White and Sable', '12-16 years', 'Lively', 'Papillons are small, lively dogs known for their butterfly-shaped ears.', CURRENT_DATE, CURRENT_DATE);

-- Insert 20 Care Tips
INSERT INTO care_tips (title, content, created_date, modified_date) VALUES
('Daily Exercise', 'Dogs need daily exercise to stay healthy and happy. Aim for at least 30 minutes each day.', CURRENT_DATE, CURRENT_DATE),
('Proper Nutrition', 'Feed your pet a balanced diet appropriate for their age, size, and health condition.', CURRENT_DATE, CURRENT_DATE),
('Grooming Regularly', 'Regular grooming helps to maintain your pets coat and skin health.', CURRENT_DATE, CURRENT_DATE),
('Socialization', 'Expose your pet to various environments and people to help them become well-adjusted.', CURRENT_DATE, CURRENT_DATE),
('Routine Vet Check-ups', 'Schedule regular veterinary visits for vaccinations and health assessments.', CURRENT_DATE, CURRENT_DATE),
('Dental Care', 'Maintain your pet’s dental hygiene by brushing their teeth and providing dental chews.', CURRENT_DATE, CURRENT_DATE),
('Hydration', 'Ensure your pet has access to fresh, clean water at all times.', CURRENT_DATE, CURRENT_DATE),
('Training and Commands', 'Teach basic commands to enhance communication and obedience.', CURRENT_DATE, CURRENT_DATE),
('Parasite Prevention', 'Use preventive measures to protect against fleas, ticks, and worms.', CURRENT_DATE, CURRENT_DATE),
('Temperature Control', 'Provide a comfortable environment, keeping your pet safe from extreme temperatures.', CURRENT_DATE, CURRENT_DATE),
('Pet Insurance', 'Consider getting pet insurance to help cover unexpected medical expenses.', CURRENT_DATE, CURRENT_DATE),
('Travel Safety', 'Secure your pet in a crate or seatbelt during car travel.', CURRENT_DATE, CURRENT_DATE),
('Monitoring Behavior', 'Keep an eye on changes in behavior as they can indicate health issues.', CURRENT_DATE, CURRENT_DATE),
('Identification Tags', 'Ensure your pet wears a collar with ID tags and is microchipped.', CURRENT_DATE, CURRENT_DATE),
('House Training', 'Establish a consistent routine for house training your pet.', CURRENT_DATE, CURRENT_DATE),
('Interactive Play', 'Engage in interactive play to stimulate your pet’s mind and body.', CURRENT_DATE, CURRENT_DATE),
('Seasonal Care', 'Adjust care routines according to seasonal changes in weather.', CURRENT_DATE, CURRENT_DATE),
('Quality Time', 'Spend quality time with your pet to strengthen your bond.', CURRENT_DATE, CURRENT_DATE),
('Watch for Allergies', 'Be vigilant about signs of allergies, such as itching or gastrointestinal issues.', CURRENT_DATE, CURRENT_DATE),
('Safe Environment', 'Create a safe home environment, removing hazards that could harm your pet.', CURRENT_DATE, CURRENT_DATE);

-- Insert 20 Health Issues
INSERT INTO health_issues (name, description, created_date, modified_date, symptoms, treatment) VALUES
('Obesity', 'Excess weight can lead to various health problems including diabetes and joint issues.', CURRENT_DATE, CURRENT_DATE, 'Lethargy, difficulty breathing, and trouble walking.', 'Dietary changes and increased exercise.'),
('Hip Dysplasia', 'A genetic condition where the hip joint doesnt fit into the hip socket.', CURRENT_DATE, CURRENT_DATE, 'Difficulty getting up, limping, and reluctance to exercise.', 'Weight management and surgery if severe.'),
('Allergies', 'Pets can be allergic to food, pollen, or flea bites.', CURRENT_DATE, CURRENT_DATE, 'Itching, swelling, and skin infections.', 'Antihistamines and avoidance of allergens.'),
('Dental Disease', 'Plaque and tartar buildup can lead to serious dental issues.', CURRENT_DATE, CURRENT_DATE, 'Bad breath, gum inflammation, and difficulty eating.', 'Regular dental cleanings and at-home dental care.'),
('Arthritis', 'Inflammation of the joints causing pain and stiffness.', CURRENT_DATE, CURRENT_DATE, 'Limping, difficulty standing, and reluctance to play.', 'Pain relief medication and weight management.'),
('Heartworm Disease', 'A serious disease caused by parasitic worms affecting the heart and lungs.', CURRENT_DATE, CURRENT_DATE, 'Coughing, lethargy, and weight loss.', 'Preventive medications and treatment with adulticides.'),
('Ear Infections', 'Bacterial or yeast infections in the ear canal.', CURRENT_DATE, CURRENT_DATE, 'Scratching at ears, head shaking, and odor.', 'Medication to treat the infection and clean the ears.'),
('Diabetes', 'A condition where the body cannot properly process sugar.', CURRENT_DATE, CURRENT_DATE, 'Increased thirst and urination, weight loss.', 'Insulin therapy and dietary changes.'),
('Gastric Dilatation-Volvulus', 'A life-threatening condition where the stomach twists.', CURRENT_DATE, CURRENT_DATE, 'Distended abdomen, retching without vomiting.', 'Emergency surgery is required.'),
('Pancreatitis', 'Inflammation of the pancreas, often due to dietary indiscretion.', CURRENT_DATE, CURRENT_DATE, 'Vomiting, diarrhea, and abdominal pain.', 'Dietary management and medications.'),
('Skin Infections', 'Bacterial or fungal infections of the skin.', CURRENT_DATE, CURRENT_DATE, 'Redness, swelling, and discharge.', 'Antibiotics or antifungal medications.'),
('Cushings Disease', 'An endocrine disorder caused by excessive cortisol.', CURRENT_DATE, CURRENT_DATE, 'Increased thirst and urination, pot-bellied appearance.', 'Medication to control hormone levels.'),
('Hypothyroidism', 'Low thyroid hormone levels affecting metabolism.', CURRENT_DATE, CURRENT_DATE, 'Weight gain, lethargy, and skin problems.', 'Thyroid hormone replacement therapy.'),
('Kennel Cough', 'A contagious respiratory disease.', CURRENT_DATE, CURRENT_DATE, 'Dry cough, nasal discharge, and fever.', 'Cough suppressants and rest.'),
('Fleas', 'Parasitic insects that can cause itching and allergic reactions.', CURRENT_DATE, CURRENT_DATE, 'Itching, hair loss, and irritation.', 'Flea prevention treatments.'),
('Ticks', 'Parasitic arachnids that can transmit diseases.', CURRENT_DATE, CURRENT_DATE, 'Lethargy, loss of appetite, and joint pain.', 'Tick prevention and removal.'),
('Urinary Tract Infection', 'Infection of the urinary system.', CURRENT_DATE, CURRENT_DATE, 'Frequent urination, straining to urinate.', 'Antibiotics and increased water intake.'),
('Ringworm', 'A fungal infection affecting the skin.', CURRENT_DATE, CURRENT_DATE, 'Hair loss, scaly patches, and itching.', 'Antifungal treatment.'),
('Leptospirosis', 'A bacterial infection transmitted through water or soil.', CURRENT_DATE, CURRENT_DATE, 'Fever, vomiting, and lethargy.', 'Antibiotics and supportive care.'),
('Bloat', 'A serious condition where the stomach fills with gas and can twist.', CURRENT_DATE, CURRENT_DATE, 'Restlessness, distended abdomen, and retching.', 'Emergency surgery and preventive measures.');

-- Insert 20 Nutrition Guides
INSERT INTO nutrition_guides (title, content, dietary_requirements, created_date, modified_date) VALUES
('Understanding Dog Nutrition', 'A guide to balanced nutrition for dogs including proteins, fats, and carbohydrates.', 'High-quality proteins, low fillers.', CURRENT_DATE, CURRENT_DATE),
('Feeding Puppies', 'Puppies have specific nutritional needs for growth and development.', 'High protein and fat content.', CURRENT_DATE, CURRENT_DATE),
('Choosing the Right Dog Food', 'Tips on selecting the right commercial food for your dogs needs.', 'Complete and balanced diet.', CURRENT_DATE, CURRENT_DATE),
('Raw Diets', 'Exploring the benefits and risks of raw feeding for dogs.', 'Raw meats, bones, and vegetables.', CURRENT_DATE, CURRENT_DATE),
('Homemade Dog Food', 'How to prepare healthy meals for your dog at home.', 'Balanced ingredients including proteins and grains.', CURRENT_DATE, CURRENT_DATE),
('Food Allergies in Dogs', 'Identifying and managing food allergies in dogs.', 'Limited ingredient diets.', CURRENT_DATE, CURRENT_DATE),
('Hydration Needs', 'Understanding the importance of water in your dogs diet.', 'Fresh, clean water at all times.', CURRENT_DATE, CURRENT_DATE),
('Senior Dog Nutrition', 'Nutritional considerations for aging dogs.', 'Lower calorie, high fiber diets.', CURRENT_DATE, CURRENT_DATE),
('Weight Management', 'Strategies for maintaining a healthy weight in dogs.', 'Controlled portions and regular exercise.', CURRENT_DATE, CURRENT_DATE),
('Supplements for Dogs', 'When and how to use supplements in your dogs diet.', 'Vitamins, minerals, and omega fatty acids.', CURRENT_DATE, CURRENT_DATE),
('Feeding Schedule', 'Establishing a feeding routine for your dog.', 'Consistent timing and portion control.', CURRENT_DATE, CURRENT_DATE),
('Transitioning Foods', 'How to safely transition your dog to a new food.', 'Gradual change over 7-10 days.', CURRENT_DATE, CURRENT_DATE),
('Common Dog Health Issues', 'Nutritional management for common health problems.', 'Specific diets for conditions like diabetes or allergies.', CURRENT_DATE, CURRENT_DATE),
('Understanding Dog Labels', 'How to read and understand pet food labels.', 'Look for named ingredients and avoid fillers.', CURRENT_DATE, CURRENT_DATE),
('The Importance of Fiber', 'How fiber benefits your dogs digestive health.', 'Vegetables and whole grains.', CURRENT_DATE, CURRENT_DATE),
('Managing Food Portions', 'Guidelines for portion control based on your dogs weight.', 'Regularly weigh your dog and adjust portions.', CURRENT_DATE, CURRENT_DATE),
('Treats and Snacks', 'Healthy options for dog treats and snacks.', 'Low-calorie, healthy ingredients.', CURRENT_DATE, CURRENT_DATE),
('Feeding During Training', 'Best practices for feeding during training sessions.', 'Small, high-value treats.', CURRENT_DATE, CURRENT_DATE),
('Traveling with Your Dog', 'Nutritional considerations when traveling with your pet.', 'Portable, easy-to-serve meals.', CURRENT_DATE, CURRENT_DATE),
('Adopting a New Dog', 'Nutritional guidelines for new dog owners.', 'Start with the food the dog is used to.', CURRENT_DATE, CURRENT_DATE);